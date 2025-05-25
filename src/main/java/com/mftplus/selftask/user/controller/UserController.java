package com.mftplus.selftask.user.controller;

import com.mftplus.selftask.permission.model.service.PermissionService;
import com.mftplus.selftask.role.model.service.RoleService;
import com.mftplus.selftask.user.model.entity.User;
import com.mftplus.selftask.user.model.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
//@Controller
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final PermissionService permissionService;

    public UserController(UserService userService, RoleService roleService, PermissionService permissionService) {
        this.userService = userService;
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.update(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/username/{username}")
    public void deletePerson(@PathVariable String username) {
        userService.deleteById(username);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserById(@PathVariable String username) {
        User user = userService.findById(username);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/roleName/{roleName}")
    public ResponseEntity<Set<User>> getPatientByRoleId(@PathVariable String roleName) {
        Set<User> userRole = userService.findByRoleSet(roleName);
        return ResponseEntity.ok(userRole);
    }

    //    @GetMapping
//    public String listUsers(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "password") String sortBy,
//            @RequestParam(required = false) Boolean fragment,
//            Model model
//    ) {
//        if (size <= 0) size = 10;
//        if (!Arrays.asList("password").contains(sortBy)) {
//            sortBy = "password";
//        }
//        Sort sort = Sort.by(sortBy).ascending();
//        Pageable pageable = PageRequest.of(page, size, sort);
//        Page<User> users = userService.getAllUser(pageable);
//        model.addAttribute("users", users);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPage", users.getTotalPages());
//
//        return fragment != null && fragment ?
//                "user/fragments/users-table :: users-table" : "user/users";
//    }
//    @GetMapping
//    public String listUsers(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "password") String sortBy,
//            @RequestParam(required = false) Boolean fragment,
//            Model model
//    ) {
//        // Ensure the size is greater than 0
//        if (size <= 0) size = 10;
//
//        // Validate sortBy field, if it is invalid set to default
//        if (!Arrays.asList("password").contains(sortBy)) {
//            sortBy = "password";
//        }
//
//        // Set sorting order
//        Sort sort = Sort.by(sortBy).ascending();
//        Pageable pageable = PageRequest.of(page, size, sort);
//
//        // Retrieve the list of users with pagination
//        Page<User> usersPage = userService.getAllUser(pageable);
//
//        // Map the User entities to UserDto objects, including roleNames as Set<String>
//        Page<UserDto> userDtoPage = usersPage.map(user -> {
//            // Collect roleNames from the user's roleSet
//            Set<String> roleNames = user.getRoleSet().stream()
//                    .map(Role::getRoleName)
//                    .collect(Collectors.toSet());
//
//            // Create UserDto and populate it with user details and roleNames
//            UserDto dto = new UserDto();
//            dto.setUsername(user.getUsername());
//            dto.setPassword(user.getPassword());
//            dto.setLocked(user.isLocked());
//            dto.setRoleNames(roleNames); // Set role names here
//            return dto;
//        });
//
//        // Add the necessary attributes to the model
//        model.addAttribute("users", userDtoPage);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPage", userDtoPage.getTotalPages());
//
//        // Return the appropriate view based on whether it's a fragment request or full page
//        return fragment != null && fragment
//                ? "user/fragments/users-table :: users-table"
//                : "user/users";
//    }
//
//
//
////    @GetMapping
////    public String getUsers(Model model) {
////        model.addAttribute("user", new User());
////        model.addAttribute("users", userService.findAll());
////        return "users";
////    }
////
////    @GetMapping("/username/{username}")
////    public String getUserByUsername(Model model, @PathVariable String username) {
////        if (userService.findById(username) != null) {
////            log.info("user found by username {}", userService.findById(username));
////            model.addAttribute("user", userService.findById(username));
////            return "redirect:/users";       //todo->maybe must be "editUser"
////
////        } else {
////            log.error("user not found by username");
////            return null;
////        }
////    }
//
//    @GetMapping("/roleName/{roleName}")
//    public String getUserByRoleName(Model model, @PathVariable String roleName) {
//        if (userService.findByRoleSet(roleName) != null) {
//            log.info("user found by role name {}", userService.findByRoleSet(roleName));
//            model.addAttribute("roleName", userService.findByRoleSet(roleName));
//            return "redirect:/users";
//
//        } else {
//            log.error("user not found by role name");
//            return null;
//        }
//    }
//
//    @GetMapping("/password/{password}")
//    public String getUserByPassword(Model model, @PathVariable String password) {
//        if (userService.findByPassword(password) != null) {
//            log.info("user found by password {}", userService.findByPassword(password));
//            model.addAttribute("user", userService.findByPassword(password));
//            return "redirect:/users";
//
//        } else {
//            log.error("user not found by password");
//            return null;
//        }
//    }
//
//    //    @PostMapping
////    @ResponseBody
////    public ResponseEntity<?> saveUser(
////            @Valid @RequestBody User user,
////            BindingResult bindingResult
////    ) {
////        log.info("User Saved {}",userService.save(user));
////        if (bindingResult.hasErrors()) {
////            Map<String, String> errors = new HashMap<>();
////            bindingResult.getFieldErrors().forEach(error ->
////                    errors.put(error.getField(), error.getDefaultMessage())
////            );
////            return ResponseEntity.badRequest().body(errors);
////        }
////
////        try {
////            User savedUser = userService.save(user);
////            return ResponseEntity.ok(savedUser);
////        } catch (Exception e) {
////            return ResponseEntity.badRequest().body(e.getMessage());
//////            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
////        }
////    }
//    @PostMapping
//    @Transactional(rollbackOn = Exception.class)
//    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto) {
//        User user = userDto.toEntity(roleService); // نقش‌ها رو هم ست می‌کنه
//        userService.save(user);
//        return ResponseEntity.ok("User saved");
//
//}
//
//
//
//    @PutMapping
//    public String updateUser(User user) {
//        userService.update(user);
//        log.info("updated user {}", user);
//        return "redirect:/users";
//
//    }
//
//
//    @DeleteMapping("/{username}")
//    @ResponseBody
//    public ResponseEntity<?> deleteUserByUsername(@PathVariable String username) {
//        try {
////            System.out.println("deleted user by username");
//            userService.deleteById(username);
//            log.info("deleted");
//
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
////            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
//        }
//    }
}
