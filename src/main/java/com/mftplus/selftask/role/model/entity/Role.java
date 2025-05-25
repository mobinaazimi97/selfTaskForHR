package com.mftplus.selftask.role.model.entity;

import com.mftplus.selftask.permission.model.entity.Permission;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name="roleEntity")
@Table(name="role_tbl")
public class Role {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(name = "role_name", length = 30)
    private String roleName;

    private boolean locked;

    @ManyToMany(fetch = FetchType.EAGER)
//    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permission_tbl",
            joinColumns = @JoinColumn(name = "role_name"),
            inverseJoinColumns = @JoinColumn(name = "permission_name"),
            foreignKey = @ForeignKey(name = "fk_role_permission"),
            inverseForeignKey = @ForeignKey(name = "fk_inverse_role_permission")
    )
    private Set<Permission> permissionSet;

//    @ManyToOne
//    @JoinColumn(name = "username")
//    private User user;

//    public void addPermission(Permission permission){
//        if(permissionSet == null){
//            permissionSet = new HashSet<>();
//        }
//        permissionSet.add(permission);
//    }
}
