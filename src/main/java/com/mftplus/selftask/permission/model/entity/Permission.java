package com.mftplus.selftask.permission.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name="permissionEntity")
@Table(name="permission_tbl")
public class Permission {
    @Id
    @Column(name = "permission_name")
    private String permissionName;

    private boolean locked;

//    @ManyToOne
//    @JoinColumn(name = "role_name")
//    private Role role;
//    @SequenceGenerator(name = "permissionSeq", sequenceName = "permission_seq", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permissionSeq")
//    @Column(name = "id")
//    private Long id;

}
