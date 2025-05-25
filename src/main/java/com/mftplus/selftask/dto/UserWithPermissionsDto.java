package com.mftplus.selftask.dto;


import lombok.Getter;

import java.util.Set;
@Getter

public class UserWithPermissionsDto {
    private String username;
    private String password;
    private boolean locked;
    private Set<PermissionDto> permissionsSet;  // Set<PermissionDto> for permissions.

    // Constructor
    public UserWithPermissionsDto(String username, String password, boolean locked, Set<PermissionDto> permissionsSet) {
        this.username = username;
        this.password = password;
        this.locked = locked;
        this.permissionsSet = permissionsSet;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLocked() {
        return locked;
    }

    public Set<PermissionDto> getPermissionsSet() {
        return permissionsSet;
    }
    public void setPermissionsSet(Set<PermissionDto> permissionsSet) {
        this.permissionsSet = permissionsSet;
    }

    public UserWithPermissionsDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserWithPermissionsDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserWithPermissionsDto setLocked(boolean locked) {
        this.locked = locked;
        return this;
    }

    public UserWithPermissionsDto() {
    }
}
