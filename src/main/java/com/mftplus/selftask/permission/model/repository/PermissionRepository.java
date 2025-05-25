package com.mftplus.selftask.permission.model.repository;

import com.mftplus.selftask.permission.model.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
//    Page<Permission> findAll(Pageable pageable);

    Permission findByPermissionName(String permissionName);
}
