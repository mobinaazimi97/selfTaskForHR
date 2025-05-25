package com.mftplus.selftask.permission.controller;

import com.mftplus.selftask.permission.model.entity.Permission;
import com.mftplus.selftask.permission.model.service.PermissionService;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@Controller
@RequestMapping("/permissions")
@Slf4j
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission) {
        permissionService.save(permission);
        return ResponseEntity.status(HttpStatus.OK).body(permission);
    }

    @PutMapping
    public ResponseEntity<Permission> updatePermission(@RequestBody Permission permission) {
        permissionService.update(permission);
        return ResponseEntity.status(HttpStatus.OK).body(permission);
    }

    @DeleteMapping("/permissionName/{permissionName}")
    public void deletePermission(@PathVariable String permissionName) {
        permissionService.delete(permissionName);
    }

    @GetMapping
    public ResponseEntity<List<Permission>> getAllPermissions() {
        return ResponseEntity.ok(permissionService.findAll());
//        List<Patient> patients = patientService.findAll();
//        return ResponseEntity.ok(patients);
    }

    @GetMapping("/permissionName/{permissionName}")
    public ResponseEntity<Permission> getPermissionByName(@PathVariable String permissionName) {
        Permission permission = permissionService.findByPermissionName(permissionName);
        return ResponseEntity.status(HttpStatus.OK).body(permission);

//        return patient != null ? ResponseEntity.ok(patient) : ResponseEntity.notFound().build();
    }

//    @GetMapping
//    public String listPermissions(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "permissionName") String sortBy,
//            @RequestParam(required = false) Boolean fragment,
//            Model model
//    ) {
//        if (size <= 0) size = 10;
//        if (!Arrays.asList("permissionName").contains(sortBy)) {
//            sortBy = "permissionName";
//        }
//        Sort sort = Sort.by(sortBy).ascending();
//        Pageable pageable = PageRequest.of(page, size, sort);
//        Page<Permission> permissions = permissionService.getAllPermission(pageable);
//        model.addAttribute("permissions", permissions);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPage", permissions.getTotalPages());
//
//        return fragment != null && fragment ?
//                "permission/fragments/permissions-table :: permissions-table" : "permission/permissions";
//    }
//
//    @PostMapping
//    @ResponseBody
//    public ResponseEntity<?> savePermission(
//            @Valid @RequestBody Permission permission,
//            BindingResult bindingResult
//    ) {
//        log.info("Permission Saved {}",permissionService.save(permission));
//        if (bindingResult.hasErrors()) {
//            Map<String, String> errors = new HashMap<>();
//            bindingResult.getFieldErrors().forEach(error ->
//                    errors.put(error.getField(), error.getDefaultMessage())
//            );
//            return ResponseEntity.badRequest().body(errors);
//        }
//
//        try {
//            Permission savedPermission = permissionService.save(permission);
//            return ResponseEntity.ok(savedPermission);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
//        }
//    }
//
//    @PutMapping
//    public String updatePermission(Permission permission) {
//        permissionService.update(permission);
//        log.info("updated permission {}", permission);
//        return "redirect:/permissions";
//
//    }
//
//    @DeleteMapping("/{permissionName}")
//    @ResponseBody
//    public ResponseEntity<?> deletePermissionByPermissionName(@PathVariable String permissionName) {
//        try {
//            permissionService.delete(permissionName);
//            log.info("deleted");
//
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
//        }
//    }
}
