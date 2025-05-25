package com.mftplus.selftask.dto;

import com.mftplus.selftask.role.model.entity.Role;
import com.mftplus.selftask.role.model.repository.RoleRepository;
import com.mftplus.selftask.role.model.service.RoleService;
import com.mftplus.selftask.user.model.entity.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserDto {
    private String username;
    private String password;
    private boolean locked;
    private Set<String> roleNames = new HashSet<>(); // فقط roleName ها از سمت UI میاد

    // Constructors
    public UserDto() {
    }

    public UserDto(String username, String password, boolean locked, Set<String> roleNames) {
        this.username = username;
        this.password = password;
        this.locked = locked;
        this.roleNames = roleNames;
    }

    // Getters & Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Set<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(Set<String> roleNames) {
        this.roleNames = roleNames;
    }

    // تبدیل به Entity
    public User toEntity(RoleService roleService) {
        Set<Role> roles = Optional.ofNullable(this.getRoleNames())
                .orElse(Collections.emptySet())
                .stream()
                .map(roleService::findByRoleName)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        return User.builder()
                .username(this.username)
                .password(this.password)
                .locked(this.locked)
                .roleSet(roles)
                .build();
    }
}
