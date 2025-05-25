package com.mftplus.selftask.role.controller;


import com.mftplus.selftask.role.model.entity.Role;
import com.mftplus.selftask.role.model.service.RoleService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
//@Controller
@RequestMapping("/roles")
@Slf4j
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        roleService.save(role);
        return ResponseEntity.status(HttpStatus.OK).body(role);
    }

    @PutMapping
    public ResponseEntity<Role> updateRole(@RequestBody Role role) {
        roleService.update(role);
        return ResponseEntity.status(HttpStatus.OK).body(role);
    }

    @DeleteMapping("/roleName/{roleName}")
    public void deleteRole(@PathVariable String roleName) {
        roleService.delete(roleName);
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/roleName/{roleName}")
    public ResponseEntity<Role> getPatientByRoleName(@PathVariable String roleName) {
        Role role = roleService.findByRoleName(roleName);
        return ResponseEntity.ok(role);
    }

    @GetMapping("/permissionName/{permissionName}")
    public ResponseEntity<Set<Role>>getPatientByPermissionName(@PathVariable String permissionName) {
        return ResponseEntity.ok(roleService.findByPermissionSet(permissionName));
//        Set<Role> rolePerms = roleService.findByPermissionSet(permissionName);
//        return ResponseEntity.ok(rolePerms);

    }
//    @GetMapping
//    public String listRoles(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "roleName") String sortBy,
//            @RequestParam(required = false) Boolean fragment,
//            Model model
//    ) {
//        if (size <= 0) size = 10;
//        if (!Arrays.asList("roleName").contains(sortBy)) {
//            sortBy = "roleName";
//        }
//        Sort sort = Sort.by(sortBy).ascending();
//        Pageable pageable = PageRequest.of(page, size, sort);
//        Page<Role> roles = roleService.getAllRole(pageable);
//        model.addAttribute("roles", roles);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPage", roles.getTotalPages());
//
//        return fragment != null && fragment ?
//                "role/fragments/roles-table :: roles-table" : "role/roles";
//    }
//
//    @PostMapping
//    @ResponseBody
//    public ResponseEntity<?> saveRole(
//            @Valid @RequestBody Role role,
//            BindingResult bindingResult
//    ) {
//        log.info("Role Saved {}",roleService.save(role));
//        if (bindingResult.hasErrors()) {
//            Map<String, String> errors = new HashMap<>();
//            bindingResult.getFieldErrors().forEach(error ->
//                    errors.put(error.getField(), error.getDefaultMessage())
//            );
//            return ResponseEntity.badRequest().body(errors);
//        }
//
//        try {
//            Role savedRole = roleService.save(role);
//            return ResponseEntity.ok(savedRole);
//        } catch (Exception e) {
////            return ResponseEntity.badRequest().body(e.getMessage());
//            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
//        }
//    }
//
//    @PutMapping
//    public String updateRole(Role role) {
//        roleService.update(role);
//        log.info("updated role {}", role);
//        return "redirect:/roles";
//
//    }
//
//    @DeleteMapping("/{roleName}")
//    @ResponseBody
//    public ResponseEntity<?> deleteRoleByRoleName(@PathVariable String roleName) {
//        try {
//            roleService.delete(roleName);
//            log.info("deleted");
//
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
//        }
//    }

}
