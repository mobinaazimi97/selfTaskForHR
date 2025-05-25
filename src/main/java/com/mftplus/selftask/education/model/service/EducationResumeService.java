package com.mftplus.selftask.education.model.service;

import com.mftplus.selftask.education.model.entity.EducationResume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EducationResumeService {
    EducationResume save(EducationResume educationResume);
    EducationResume update(EducationResume educationResume);
    void delete(Long id);
    List<EducationResume> findAll();
//    List<EducationResume>findByFirstName(String firstName);
//    List<EducationResume>findByLastName(String lastName);
    EducationResume findById(Long id);
    Page<EducationResume> getAllEducationResume(Pageable pageable);
    List<EducationResume> findByDegreeOfStudy(String degreeOfStudy);
    EducationResume findByStudentNumber(String studentNumber);
    List<EducationResume> findByPerson(Long id);



}
