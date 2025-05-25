package com.mftplus.selftask.user.model.service;

import com.mftplus.selftask.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface UserService {
    User save(User user);

    User update(User user);

    void deleteById(String username);

    User findById(String username);

    List<User> findByPassword(String password);

    boolean existsByUsername(String username);

    List<User> findAll();
//    Page<User>getAllUser(Pageable pageable);

    Set<User> findByRoleSet(String roleName);

    Set<User> findRoleSetPermission(String permissionName);

}
