package com.mftplus.selftask.person.model.repository;

import com.mftplus.selftask.person.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

//    Page<Person> findAll(Pageable pageable);

    @Query("select p from personEntity p where p.lastName=:lastName")
    List<Person> findByLastName(@Param("lastName") String lastName);

    @Query("select p from personEntity p where p.firstName=:firstName")
    List<Person> findByFirstName(@Param("firstName") String firstName);

    @Query("select p from personEntity p where p.email=:email")
    Person findByEmail(@Param("email") String email);

    //  @Query("select p from personEntity p left join userEntity u where u.username=:username")
    @Query("select p from personEntity p cross join userEntity u where u.username = :username")
//    @Query("select p.id ,p.user.username from personEntity p join  PERSON_USER pu on p.id=:pu.personId join  userEntity u on pu.username=:u.username")
    List<Person> findByUser(@Param("username") String username);

    @Query("select p from personEntity p cross join userEntity u where u.password = :password")
//    @Query("select p.id ,p.user.username from personEntity p join  PERSON_USER pu on p.id=:pu.personId join  userEntity u on pu.username=:u.username")
    List<Person> findByUserPassword(@Param("password") String password);

//    @Query("select p from personEntity p cross join educationResumeEntity e where e.degreeOfStudy=:degreeOfStudy")
//    List<Person> findByDegreeOfStudy(String degreeOfStudy);
//
//    @Query("select p from personEntity p cross join educationResumeEntity e where e.studentNumber=:studentNumber")
//    Person findByStudentNumber(String studentNumber);
}
