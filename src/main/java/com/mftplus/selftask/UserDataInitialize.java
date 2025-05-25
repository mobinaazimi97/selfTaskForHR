package com.mftplus.selftask;

import com.mftplus.selftask.education.model.entity.EducationResume;
import com.mftplus.selftask.education.model.service.EducationResumeService;
import com.mftplus.selftask.permission.model.entity.Permission;
import com.mftplus.selftask.permission.model.service.PermissionService;
import com.mftplus.selftask.person.model.entity.Person;
import com.mftplus.selftask.person.model.service.PersonService;
import com.mftplus.selftask.role.model.entity.Role;
import com.mftplus.selftask.role.model.service.RoleService;
import com.mftplus.selftask.user.model.entity.User;
import com.mftplus.selftask.user.model.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class UserDataInitialize implements CommandLineRunner {
    private final EducationResumeService educationResumeService;
    private final PersonService personService;
    private final PermissionService permissionService;
    private final UserService userService;
    private final RoleService roleService;

    public UserDataInitialize(EducationResumeService educationResumeService, PersonService personService, PermissionService permissionService, UserService userService, RoleService roleService) {
        this.educationResumeService = educationResumeService;
        this.personService = personService;
        this.permissionService = permissionService;
        this.userService = userService;
        this.roleService = roleService;
    }


    @Override
    public void run(String... args) throws Exception {

        Permission permission = Permission.builder().permissionName("Access_All_HR").build();
        permissionService.save(permission);
//        log.info("Permission save {}",permissionService.save(permission));

        Permission permission1 = Permission.builder().permissionName("Access_HR_SALARY").build();
        permissionService.save(permission1);
//        log.info("Permission 1 save {}",permissionService.save(permission1));
        permissionService.findByPermissionName("Access_HR_SALARY");
//        log.info("Permission found {}",permissionService.findByPermissionName("Access_HR_SALARY"));

// -----------------------------------------------------------------------------------------------------------

        Role role1 = Role.builder().permissionSet(Set.of(permission)).roleName("admin").build();
//        role1.setPermissionSet(rolePermissionDto.addPermission(permission));
        role1.setPermissionSet(Set.of(permission));
        roleService.save(role1);
        log.info("Role save initialize {}",roleService.save(role1));
//        log.info("roleName found {}",roleService.findByRoleName("admin"));
//        log.info("roles permissions {}",role1.getPermissionSet());
//        roleService.findByPermissionSet("Access_All_HR");
//        log.info("role found by permission {}",roleService.findByPermissionSet("Access_All_HR"));
// ------------------------------------------------------------------------------------------------------------------------------

        Role role2 = Role.builder().permissionSet(Set.of(permission1)).roleName("admin").build();
        roleService.save(role2);
//        log.info("Role 1 save {}",roleService.save(role2));

        Role role3 = Role.builder().permissionSet(Set.of(permission1,permission)).roleName("manager").build();
        roleService.save(role3);
//        log.info("role 3 saved {}",roleService.save(role3));

        System.out.println("Permission U wanted :"+roleService.findByPermissionSet("Access_All_HR"));
//-----------------------------------------------------------------------------------------------------------------------------------
//        User user = User.builder().roleSet(Set.of(role1)).username("aa").password("m123").locked(false).build();
        User user= new User();
        user.setRoleSet(Set.of(role1));
        user.setUsername("aa");
        user.setPassword("m123");
//        user.isLocked()=false;
        userService.save(user);
//        log.info("User initialized {}",userService.save(user));
        //-----------------------------------------------------------------------------------------------------------------------------
//        User user1 = User.builder().roleSet(Set.of(role1)).username("mo123").password("jk34").locked(false).build();
//        userService.save(user1);
//        log.info("User1 initialized {}", userService.save(user1));
//
//        List<User> userList=new ArrayList<>();
//        userList.add(user1);

//        userService.findByRoleSet("admin");
//        log.info("The Role Of User Found  {}",userService.findByRoleSet("admin"));

//        userService.findByUsername("bb");
//        userService.findByPassword(user1.getPassword());
//        log.info("User found by password {}",userService.findByPassword(user1.getPassword()));

//        userService.findRoleSetPermission("Access_HR_SALARY");
//        log.info("permissions user found{}",userService.findRoleSetPermission("Access_HR_SALARY"));

//        log.info("User 1 initialized {}",userService.save(user1));
//        log.info("user found by role name{}",userService.findByRoleSet("admin"));
//        log.info("user found by permission name{}", userService.findRoleSetPermission("Access_All_HR"));
//        log.info("Users initialized by role  {}",userService.findByRoleSet("admin"));

//        roleService.findByPermissionSet("Access_All_HR");
//        log.info("role permission found {}",roleService.findByPermissionSet("Access_All_HR"));

//        userService.findByRoleSet("admin");
//        userService.findById("mobi");
//        log.info("username is {}", userService.findById("mobi"))
//        ;
//        userService.deleteById("mobi");

//        user.setPassword("123");
//        userService.update(user);
//        log.info("username is {}", userService.findAll());
//        log.info("User found by role{}",userService.findByRoleSet("admin"));


//        userService.findByUsername("john");
//        log.info("User found by username{}", userService.findByUsername("john"));


        //        userService.delete(user.getUsername());
//        log.info("User deleted");
//        ----------------------------------------------------------------------------------------------------------------
//        EducationResume educationResume=EducationResume.builder().studentNumber("123").degreeOfStudy("phd").build();
//        educationResumeService.save(educationResume);

//        log.info("EducationResume initialized {}", educationResumeService.save(educationResume));

//        -------------------------------------------------------------------------------------------------------------------

        Person person=Person.builder().lastName("azx").firstName("mobina").phone("09123224334").email("aaa@.com").build();
        personService.save(person);
        log.info("Person initialized {}", personService .save(person));
//        personService.findByDegreeOfStudy("phd");
//        log.info("Person found {}", personService.findByDegreeOfStudy("phd"));
//        log.info("person found by study number{}",personService.findByStudentNumber("123"));
//        personService.findByUser("mo123");
//        log.info("Person User initialized {}", personService .findByUser("mo123"));

        Person person1=Person.builder().user(user).lastName("abc").firstName("cda").phone("09123224334").email("cccvv@.com").build();
        personService.save(person1);
//        log.info("Person initialized {}", personService .save(person1));
        //        ----------------------------------------------------------------------------------------------------------------
        EducationResume educationResume=EducationResume.builder().personList(List.of(person1)).studentNumber("123").degreeOfStudy("phd").build();
        educationResumeService.save(educationResume);
//        educationResumeService.findByPerson(1L);

//        log.info("EducationResume initialized {}", educationResumeService.save(educationResume));
//        log.info("EducationResume found by person id initialized {}", educationResumeService.findByPerson(1L));


//        --------------------------------------------------------------------------------------------------------------

        User user3=new User();
        user3.setUsername("mobiii");
        user3.setPassword("321n");
        userService.save(user3);


    }
}
