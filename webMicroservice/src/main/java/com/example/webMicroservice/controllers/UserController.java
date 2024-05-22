package com.example.webMicroservice.controllers;

import com.example.common.models.Role;
import com.example.common.models.CatOwnerUser;
import com.example.common.repository.RoleRepository;
import com.example.common.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody CatOwnerUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    @PostMapping("/role")
    public ResponseEntity<String> createRole(@RequestBody Role role) {
        roleRepository.save(role);
        return new ResponseEntity<>("Role created", HttpStatus.CREATED);
    }

    @PostMapping("/user/{userId}/role/{roleId}")
    public ResponseEntity<String> addRoleToUser(@PathVariable UUID userId, @PathVariable UUID roleId) {
        CatOwnerUser user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        user.getRoles().add(role);
        userRepository.save(user);
        return new ResponseEntity<>("Role added to user", HttpStatus.OK);
    }
}
