package com.intern.Internship.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
public class Company implements HasID<String> {
    private static final long serialVersionUID = -9090476835116308556L;

    @Id
    @Column(name = "email")
    private String ID; // email

    private String name;
    private String address;
    private String telephone;
    private String description;
    private String field;
    @Lob
    @Column(name = "logo", columnDefinition = "BLOB")
    private byte[] logo;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Internship> internships;

    /**
     * Company constructor
     * 
     * @param ID:          String, e-mail address
     * @param name:        String, full name
     * @param address:     String
     * @param telephone:   String
     * @param description: String
     * @param field:       String
     * @param logo:        Blob
     * @param internships: Internships...
     */
    public Company(String ID, String name, String address, String telephone, String description, String field,
            byte[] logo, Internship... internships) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.description = description;
        this.field = field;
        this.logo = logo;
        this.internships = Stream.of(internships).collect(Collectors.toSet());
        this.internships.forEach(x -> x.setCompany(this));
    }
}
