package com.mftplus.selftask.person.model.service;

import com.mftplus.selftask.person.model.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonService {
    Person save(Person person);

    Person update(Person person);

    void delete(Long id);

    //    Page<Person> getAllPerson(Pageable pageable);
    List<Person> findAll();

    List<Person> findByFirstName(String firstName);

    List<Person> findByLastName(String lastName);

    Person findByEmail(String email);

    Person findById(Long id);

    List<Person> findByUser(String username);

    List<Person> findByUserPassword(String password);


//    Person findByStudentNumber(String studentNumber);
//
//    List<Person> findByDegreeOfStudy(String degreeOfStudy);


}
