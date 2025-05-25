package com.mftplus.selftask.user.model.service.impl;


import com.mftplus.selftask.dto.UserDto;
import com.mftplus.selftask.permission.model.entity.Permission;
import com.mftplus.selftask.permission.model.repository.PermissionRepository;
import com.mftplus.selftask.role.model.entity.Role;
import com.mftplus.selftask.role.model.repository.RoleRepository;
import com.mftplus.selftask.user.model.entity.User;
import com.mftplus.selftask.user.model.repository.UserRepository;
import com.mftplus.selftask.user.model.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
//    private BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.userRepository = userRepository;

        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(String username) {
        userRepository.deleteById(username);
    }


//    @Override
//    public List<User> findAll() {
//      return userRepository.findAll();
//
//    }

    @Override
    public User findById(String username) {
//        return userRepository.findByUsername(username);
        return userRepository.findById(username).orElse(null);


    }

    @Override
    public List<User> findByPassword(String password) {
        return userRepository.findByPassword(password);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsUserByUsername(username);
    }

//    @Override
//    public Page<User> getAllUser(Pageable pageable) {
//        return userRepository.findAll(pageable);
//    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public Set<User> findByRoleSet(String roleName) {
        Set<User> users = new HashSet<>();
        User user = new User();
        Role role = roleRepository.findByRoleName(roleName);

        if (users.isEmpty()) {
            user.setRoleSet(Set.of(role));
            users.add(user);
            return userRepository.findByRoleSet(roleName);
        } else {
            return users;
        }
    }

    @Override
    public Set<User> findRoleSetPermission(String permissionName) {
        Set<User> users = new HashSet<>();
        User user = new User();
        Set<Role> roleSet = new HashSet<>();
        Role role = new Role();
       Permission permission=permissionRepository.findByPermissionName(permissionName);

       if (roleSet.isEmpty()) {
            role.setPermissionSet((Set.of(permission)));
            roleSet.add(role);
            user.setRoleSet(roleSet);
            users.add(user);
            return userRepository.findRoleSetPermission(permissionName);
        }else {
            return users;
        }
    }
}
