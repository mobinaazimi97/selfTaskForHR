package com.mftplus.selftask.person.model.entity;

import com.mftplus.selftask.user.model.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "personEntity")
@Table(name = "person_tbl")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "personSeq", sequenceName = "person_seq", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personSeq")
//    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false, length = 30)
    @NotBlank(message = "{validation.firstname}")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 30)
    @NotBlank(message = "{validation.lastname}")
    private String lastName;

    //    @Column(name = "email", length = 50)
//    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "{validation.email}")
    private String email;

    //    @Column(name = "phone", unique = true, length = 15)
    @Pattern(regexp = "^[0-9]{8,}$", message = "{validation.phone}")
    private String phone;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "person_user",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "username"),
            foreignKey = @ForeignKey(name = "fk_person_user"))
    private User user;


//    todo-> get docs from person
//    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
//    @JoinTable(name = "person_educattion",
//            joinColumns = @JoinColumn(name = "id"),
//            inverseJoinColumns = @JoinColumn(name = "educateId"),
//            foreignKey = @ForeignKey(name = "fk_person_education"))
//    private List<EducationResume> educationResumeList;
}
