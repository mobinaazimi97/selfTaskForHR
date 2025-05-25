package com.mftplus.selftask.role.model.service.impl;

import com.mftplus.selftask.permission.model.entity.Permission;
import com.mftplus.selftask.permission.model.repository.PermissionRepository;
import com.mftplus.selftask.role.model.entity.Role;
import com.mftplus.selftask.role.model.repository.RoleRepository;
import com.mftplus.selftask.role.model.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleServiceImpl(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }


    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void delete(String roleName) {
        roleRepository.deleteById(roleName);

    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

//    @Override
//    public Page<Role> getAllRole(Pageable pageable) {
//        return roleRepository.findAll(pageable);
//    }

//    @Override
//    public void delete(String roleName) {
//        roleRepository.deleteById(roleName);
//    }


    @Override
    public Role findByRoleName(String roleName) {
        //todo -> maybe findById...
        return roleRepository.findById(roleName).orElse(null);
    }

    @Override
    public Set<Role> findByPermissionSet(String permissionName) {
        Set<Role> roleSet = new HashSet<>();
        Role role = new Role();
        Permission permission = permissionRepository.findByPermissionName(permissionName);
        if (roleSet.isEmpty()) {
            role.setPermissionSet((Set.of(permission)));
            roleSet.add(role);
            return roleRepository.findByPermissionSet(permissionName);
        } else {
            return roleSet;
        }
    }

//        role.setPermissionSet(Set.of(permission));
//        roleSet.add(role);
//
//
//        for (Permission permission1 : role.getPermissionSet()) {
//            if (role.getPermissionSet().contains(permission)) {
//                role.setPermissionSet(Set.of(permission));
//                roleSet.add(role);
//                return roleRepository.findByPermissionSet(permissionName);
//            } else {
//                return null;
//            }
//        }
//        return null;
//    }

}
