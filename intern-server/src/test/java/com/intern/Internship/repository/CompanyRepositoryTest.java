package com.intern.Internship.repository;

import com.intern.Internship.model.AreaOfInterest;
import com.intern.Internship.model.Company;
import com.intern.Internship.model.Feedback;
import com.intern.Internship.model.Internship;
import com.intern.Internship.model.enums.InternshipStatus;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.HashSet;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CompanyRepositoryTest {
    @Autowired
    AreaOfInterestRepository areaOfInterestRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    InternshipRepository internshipRepository;

    @Test
    public void test() {
        long countBefore = companyRepository.count();
        byte[] bytes = "BLOB GOES HERE".getBytes();
        Company company1 = new Company(
                "tudor@ginga.com",
                "Company1",
                "Zambilei 12",
                "0700000000",
                "Description1",
                "Intenships",
                bytes
        );
        AreaOfInterest areaOfInterest1 = new AreaOfInterest(
                "SAP"
        );
        AreaOfInterest areaOfInterest2 = new AreaOfInterest(
                "Java"
        );
        areaOfInterestRepository.save(areaOfInterest1);
        areaOfInterestRepository.save(areaOfInterest2);
        Internship internship11 = new Internship(
                "Internship11",
                LocalDate.now(),
                LocalDate.now(),
                false,
                3,
                "Company 1 Internship 1",
                5,
                InternshipStatus.Closed,
                "Zambilei 14",
                LocalDate.now(),
                company1,
                areaOfInterest1
        );
        Internship internship12 = new Internship(
                "Internship12",
                LocalDate.now(),
                LocalDate.now(),
                true,
                4,
                "Company 1 Internship 2",
                3,
                InternshipStatus.Open,
                "Zambilei 15",
                LocalDate.now(),
                company1,
                areaOfInterest2
        );
        Feedback feedback111 = new Feedback(
                "Description1",
                true,
                5,
                null,
                internship11
        );
        Feedback feedback112 = new Feedback(
                "Description2",
                false,
                4,
                null,
                internship11
        );
        Feedback feedback121 = new Feedback(
                "Description3",
                true,
                6,
                null,
                internship12
        );
        Feedback feedback122 = new Feedback(
                "Description4",
                false,
                7,
                null,
                internship12
        );
        feedbackRepository.save(feedback111);
        feedbackRepository.save(feedback112);
        feedbackRepository.save(feedback121);
        feedbackRepository.save(feedback122);

        Feedback fromRepositoryFeedback1 = feedbackRepository.getOne(feedback111.getID());
        assert (fromRepositoryFeedback1.getInternship() != null);

        HashSet<Feedback> hashSet1 = new HashSet<>();
        hashSet1.add(feedback111);
        hashSet1.add(feedback112);
        internship11.setFeedbacks(hashSet1);
        HashSet<Feedback> hashSet2 = new HashSet<>();
        hashSet2.add(feedback121);
        hashSet2.add(feedback122);
        internship12.setFeedbacks(hashSet2);
        internshipRepository.save(internship11);
        internshipRepository.save(internship12);

        Internship fromRepositoryInternship11 = internshipRepository.getOne(internship11.getID());
        assert (fromRepositoryInternship11.getFeedbacks().size() != 0);

        HashSet<Internship> hashSet3 = new HashSet<>();
        hashSet3.add(internship11);
        hashSet3.add(internship12);
        company1.setInternships(hashSet3);
        companyRepository.save(company1);

        Company fromRepositoryCompany = companyRepository.getOne(company1.getID());
        assert (fromRepositoryCompany.getInternships().size() != 0);

        assert(companyRepository.count() == countBefore + 1);
        companyRepository.delete(company1);
        assert(companyRepository.count() == countBefore);
    }
}