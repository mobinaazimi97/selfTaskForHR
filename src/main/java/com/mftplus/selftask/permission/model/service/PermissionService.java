package com.mftplus.selftask.permission.model.service;


import com.mftplus.selftask.permission.model.entity.Permission;
import com.mftplus.selftask.role.model.entity.Role;
import com.mftplus.selftask.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PermissionService {
    Permission save(Permission permission);

    Permission update(Permission permission);

    Permission findByPermissionName(String permissionName);

    void delete(String permissionName);

    List<Permission> findAll();
//    Page<Permission> getAllPermission(Pageable pageable);
}
