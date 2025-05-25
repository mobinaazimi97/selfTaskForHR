package com.mftplus.selftask.dto;


import com.mftplus.selftask.permission.model.entity.Permission;
import com.mftplus.selftask.role.model.entity.Role;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

public class RolePermissionDto {


//    private String roleName;
//
//    private String permissionName;
//
//    public Role getRole() {
//        return Role.builder().roleName(roleName).build();
//    }
//
//    public Permission getPermission() {
//        return Permission.builder().permissionName(permissionName).build();
//
//    }
//
//    public Set<Permission> addPermission(Permission permission) {
//        Set<Permission> permissionSet = new HashSet<>();
//        permissionSet.add(permission);
//        return permissionSet;

//        Set<RolePermissionDto> rolePermissionSet = new HashSet<>();
//        for (RolePermissionDto rolePermission : rolePermissions) {
//            Role role = rolePermission.getRole();
//            Permission permission = rolePermission.getPermission();
//            role.setPermissionSet(Set.of(permission));
//            rolePermissionSet.add(rolePermission);
//
//        }
//        return rolePermissionSet;
//    }

//    }

}
