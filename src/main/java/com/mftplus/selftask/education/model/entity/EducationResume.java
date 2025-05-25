package com.mftplus.selftask.education.model.entity;

import com.mftplus.selftask.person.model.entity.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
@Entity(name = "educationResumeEntity")
@Table(name = "educationResume_tbl")
public class EducationResume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //todo(column name!!)
    @Column(name = "educateId")
    private Long id;
    private String nationalCode;
    private String firstName;
    private String lastName;
    private String fatherName;
    private LocalDate birthDate;
    private String placeOfBirth;
    private String birthCertificateNumber;
    private String placeOfIssue;
    private String universityName;
    private String fieldOfStudy;
    @Column(name = "student_Number", unique = true)
    private String studentNumber;
    private String cityOfStudy;
    private String degreeOfStudy;
    private double overallGrade;
    private LocalDate yearOfEnterInUniversity;
    private LocalDate graduationDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "personEducate",
            joinColumns = @JoinColumn(name = "educateId"),
            inverseJoinColumns = @JoinColumn(name = "id"),
            foreignKey = @ForeignKey(name = "fk_educate_person"))
    private List<Person> personList;
}
