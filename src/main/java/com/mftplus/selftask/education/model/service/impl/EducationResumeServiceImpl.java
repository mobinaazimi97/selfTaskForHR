package com.mftplus.selftask.education.model.service.impl;

import com.mftplus.selftask.education.model.entity.EducationResume;
import com.mftplus.selftask.education.model.repository.EducationResumeRepository;
import com.mftplus.selftask.education.model.service.EducationResumeService;
import com.mftplus.selftask.person.model.entity.Person;
import com.mftplus.selftask.person.model.repository.PersonRepository;
import com.mftplus.selftask.role.model.entity.Role;
import com.mftplus.selftask.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class EducationResumeServiceImpl implements EducationResumeService {
    private final EducationResumeRepository educationResumeRepository;
    private final PersonRepository personRepository;

    public EducationResumeServiceImpl(EducationResumeRepository educationResumeRepository, PersonRepository personRepository) {
        this.educationResumeRepository = educationResumeRepository;
        this.personRepository = personRepository;
    }

    @Override
    public EducationResume save(EducationResume educationResume) {
        return educationResumeRepository.save(educationResume);

    }

    @Override
    public EducationResume update(EducationResume educationResume) {
        return educationResumeRepository.save(educationResume);

    }

    @Override
    public void delete(Long id) {
        educationResumeRepository.deleteById(id);
    }

    @Override
    public List<EducationResume> findAll() {
        return educationResumeRepository.findAll();
    }

//    @Override
//    public List<EducationResume> findByFirstName(String firstName) {
//        return educationResumeRepository.findByFirstName(firstName);
//    }
//
//    @Override
//    public List<EducationResume> findByLastName(String lastName) {
//        return educationResumeRepository.findByLastName(lastName);
//    }

    @Override
    public EducationResume findById(Long id) {
        return educationResumeRepository.findById(id).orElse(null);
    }

    @Override
    public Page<EducationResume> getAllEducationResume(Pageable pageable) {
        return educationResumeRepository.findAll(pageable);
    }

    @Override
    public List<EducationResume> findByDegreeOfStudy(String degreeOfStudy) {
        return educationResumeRepository.findByDegreeOfStudy(degreeOfStudy);
    }

    @Override
    public EducationResume findByStudentNumber(String studentNumber) {
        return educationResumeRepository.findByStudentNumber(studentNumber);
    }

    @Override
    public List<EducationResume> findByPerson(Long id) {

        List<EducationResume> educationResumes = new ArrayList<>();
        EducationResume educationResume = new EducationResume();
//        Person person = personRepository.findById(id).orElse(null);
        Person person = personRepository.findById(id).orElse(null);
        if (educationResumes.isEmpty()) {
            educationResume.setPersonList(List.of(person));
            educationResumes.add(educationResume);
            return educationResumeRepository.findByPerson(id);
        }else {
            return educationResumes;
        }

//        Person person=new Person();
//        person.setId(id);

//        if (educationResumes.isEmpty()) {
//            educationResume.setPersonList(List.of(person));
//            educationResumes.add(educationResume);
//            return educationResumes;
//        } else {
//            return educationResumeRepository.findByPerson(id);
//
//        }
    }
//        Person person=personRepository.findById(id).orElse(null);
//        List<EducationResume>educationResumeList=educationResumeRepository.findByPerson(id);
//        EducationResume educationResume=educationResumeList.get(0);
//        educationResume.setPersonList(List.of(person));
//        educationResumeList.add(educationResume);
//        return educationResumeRepository.findByPerson(id);
//    }
    }
