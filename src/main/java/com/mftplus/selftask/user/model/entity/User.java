package com.mftplus.selftask.user.model.entity;

import com.mftplus.selftask.role.model.entity.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

//@NoArgsConstructor
//@Getter
//@Setter
@SuperBuilder
@ToString

@Entity(name = "userEntity")
@Table(name = "user_tbl")
public class User {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "locked")
    private boolean locked;

    @ManyToMany(fetch = FetchType.EAGER)
//    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role_tbl",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "role_name"),
            foreignKey = @ForeignKey(name = "fk_user_role"))
    private Set<Role> roleSet;

    public void addRole(Role role){
        if(roleSet == null){
            roleSet = new HashSet<>();
        }
        roleSet.add(role);
    }

    public User(String username, String password, boolean locked) {
        this.username = username;
        this.password = password;
        this.locked = locked;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isLocked() {
        return locked;
    }

    public User setLocked(boolean locked) {
        this.locked = locked;
        return this;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public User setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
        return this;
    }
}
