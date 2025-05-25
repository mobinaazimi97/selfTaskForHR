package com.mftplus.selftask.user.model.repository;

import com.mftplus.selftask.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
//todo: its true :
    @Query("select u from userEntity u where u.username=:username")
    User findByUsername(@Param("username")String username);

//    Page<User> getAllUser(Pageable pageable);

    @Query("select u from userEntity u where u.password=:password")
    List<User> findByPassword(@Param("password") String password);

    boolean existsUserByUsername(String username);

//    @Query("select u from userEntity u where u.locked=false")
//    List<User>findAll();

//    @Query("select u from userEntity u left join roleEntity r where r.roleName=:roleName")
    @Query("select u from userEntity u cross join roleEntity r where r.roleName = :roleName")
//    @Query("select u from userEntity u left join roleEntity r where r.user.username=:username")
    Set<User> findByRoleSet(@Param("roleName")String roleName);

//    @Query("select u from userEntity u cross join roleEntity r cross join permissionEntity p where p.permissionName=:permissionName")
@Query("SELECT u FROM userEntity u JOIN FETCH u.roleSet r JOIN FETCH r.permissionSet")
    Set<User> findRoleSetPermission(@Param("permissionName")String permissionName);
}
