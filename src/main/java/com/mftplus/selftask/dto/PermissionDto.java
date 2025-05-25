package com.mftplus.selftask.dto;


public class PermissionDto {
    private String permissionName;

    // Default constructor (necessary for JSON deserialization)
    public PermissionDto() {}

    // Constructor with parameters
    public PermissionDto(String permissionName) {
        this.permissionName = permissionName;
    }

    // Getter
    public String getPermissionName() {
        return permissionName;
    }

    public PermissionDto setPermissionName(String permissionName) {
        this.permissionName = permissionName;
        return this;
    }
}
