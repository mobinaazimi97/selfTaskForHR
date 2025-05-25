package com.mftplus.selftask.permission.model.service.impl;

import com.mftplus.selftask.permission.model.entity.Permission;
import com.mftplus.selftask.permission.model.repository.PermissionRepository;
import com.mftplus.selftask.permission.model.service.PermissionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }


    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission update(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission findByPermissionName(String permissionName) {
        //TODO->MAYBE FIND BY ID
        return permissionRepository.findById(permissionName).orElse(null);
//        return permissionRepository.findByPermissionName(permissionName);
    }

    @Override
    public void delete(String permissionName) {
        permissionRepository.deleteById(permissionName);
    }

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

//    @Override
//    public Page<Permission> getAllPermission(Pageable pageable) {
//        return permissionRepository.findAll(pageable);
//    }
}
