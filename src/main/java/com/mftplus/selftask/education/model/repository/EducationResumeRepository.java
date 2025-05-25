package com.mftplus.selftask.education.model.repository;

import com.mftplus.selftask.education.model.entity.EducationResume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationResumeRepository extends JpaRepository<EducationResume, Long> {
    //    List<EducationResume> findByLastName(String lastName);
//    List<EducationResume> findByFirstName(String firstName);
    List<EducationResume> findByDegreeOfStudy(String degreeOfStudy);

    EducationResume findByStudentNumber(String studentNumber);

    @Query("select e from educationResumeEntity e cross join personEntity p where p.id=:id")
    List<EducationResume> findByPerson(@Param("id") Long id);
}
