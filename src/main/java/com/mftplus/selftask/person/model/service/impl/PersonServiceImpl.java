package com.mftplus.selftask.person.model.service.impl;

import com.mftplus.selftask.person.model.entity.Person;
import com.mftplus.selftask.person.model.repository.PersonRepository;
import com.mftplus.selftask.person.model.service.PersonService;
import com.mftplus.selftask.user.model.entity.User;
import com.mftplus.selftask.user.model.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final UserRepository userRepository;

    public PersonServiceImpl(PersonRepository personRepository, UserRepository userRepository) {
        this.personRepository = personRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person update(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }


    @Override
    public List<Person> findByFirstName(String firstName) {
        return personRepository.findByFirstName(firstName);
    }

    @Override
    public List<Person> findByLastName(String lastName) {
        return personRepository.findByLastName(lastName);
    }

    @Override
    public Person findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public List<Person> findByUser(String username) {
        return personRepository.findByUser(username);
//        Person person = new Person();
//        User user = userRepository.findById(username).orElse(null);
//
//        person.setUser(user);
//        return personRepository.findByUser(username);
    }

    @Override
    public List<Person> findByUserPassword(String password) {
        return personRepository.findByUserPassword(password);
    }

//    @Override
//    public Person findByStudentNumber(String studentNumber) {
//        EducationResume educationResume = educationResumeRepository.findByStudentNumber(studentNumber);
//        Person person = new Person();
//        if (person != null) {
//            person.setEducationResumeList(List.of(educationResume));
//            return  person;
//        } else {
//            return personRepository.findByStudentNumber(studentNumber);
//        }
//
//
//    }
//
//    @Override
//    public List<Person> findByDegreeOfStudy(String degreeOfStudy) {
//        return personRepository.findByDegreeOfStudy(degreeOfStudy);
//    }

//    @Override
//    public Page<Person> getAllPerson(Pageable pageable) {
//        return personRepository.findAll(pageable);
//    }
}
