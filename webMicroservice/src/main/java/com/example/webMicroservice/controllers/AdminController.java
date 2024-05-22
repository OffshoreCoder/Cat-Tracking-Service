package com.example.webMicroservice.controllers;

import com.example.common.models.CatOwnerUser;
import com.example.common.models.Owner;
import com.example.common.repository.UserRepository;
import com.example.common.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @PostMapping("/user/{userId}/owner/{ownerId}")
    public ResponseEntity<String> assignOwnerToUser(
            @PathVariable("userId") UUID userId,
            @PathVariable("ownerId") UUID ownerId) {
        CatOwnerUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        user.setOwner(owner);
        userRepository.save(user);
        return new ResponseEntity<>("Owner assigned to user", HttpStatus.OK);
    }
}

