package com.intern.Internship.repository;

import com.intern.Internship.model.AreaOfInterest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.stream.Collectors;

public interface AreaOfInterestRepository
        extends JpaRepository<AreaOfInterest, String>, JpaSpecificationExecutor<AreaOfInterest> {

    static Specification<AreaOfInterest> multiLikeName(List<String> likePatterns) {
        return (Specification<AreaOfInterest>) (root, query, criteriaBuilder) -> {
            Path<String> column1 = root.get("name");
            // create a Predicate for each "column1 like 'xy%az%' you need
            List<Predicate> predicates = likePatterns.stream()
                    .map(likePattern -> criteriaBuilder.like(column1, likePattern)).collect(Collectors.toList());

            return criteriaBuilder.or(predicates.toArray(new Predicate[] {}));
        };
    }

    @Query("SELECT a.name from AreaOfInterest a,CandidateAreaOfInterest c WHERE  c.areaOfInterest.ID like a.ID and c.candidate.ID=?1 ")
    List<String> getAllByEmail(String email);

    @Query("SELECT a.name from AreaOfInterest a,CompanyAreaOfInterest c WHERE  c.areaOfInterest.ID like a.ID and c.company.ID=?1 ")
    List<String> getAllByEmailCompany(String email);

    AreaOfInterest findByName(String name);
}
