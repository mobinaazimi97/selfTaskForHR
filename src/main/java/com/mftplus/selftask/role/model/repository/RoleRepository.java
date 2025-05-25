package com.mftplus.selftask.role.model.repository;

import com.mftplus.selftask.role.model.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

//    Page<Role> findAll(Pageable pageable);

    @Query("select r from roleEntity r where r.roleName=:roleName")
    Role findByRoleName(@Param("roleName") String roleName);

    @Query("select r from roleEntity r cross join permissionEntity p where p.permissionName=:permissionName")
    Set<Role> findByPermissionSet(@Param("permissionName") String permissionName);
}
