package com.mftplus.selftask.role.model.service;


import com.mftplus.selftask.role.model.entity.Role;
import com.mftplus.selftask.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Set;

public interface RoleService {
    Role save(Role role);

    Role update(Role role);

    void delete(String roleName);

//    void deleteById(Long id);

    List<Role> findAll();

//    Page<Role> getAllRole(Pageable pageable);


    Role findByRoleName(String roleName);

    Set<Role> findByPermissionSet(String permissionName);

}
