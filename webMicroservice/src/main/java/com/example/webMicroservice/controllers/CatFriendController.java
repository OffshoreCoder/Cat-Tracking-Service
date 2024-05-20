package com.example.webMicroservice.controllers;

import com.example.common.models.CatFriend;
import com.example.webMicroservice.services.CatFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/catfriend")
public class CatFriendController {

    private final CatFriendService catFriendService;

    @Autowired
    public CatFriendController(CatFriendService catFriendService) {
        this.catFriendService = catFriendService;
    }

    @GetMapping("/friends/{catId}")
    public ResponseEntity<List<CatFriend>> getFriendsOfCat(@PathVariable UUID catId) {
        List<CatFriend> friends = catFriendService.getFriendsOfCat(catId);
        return new ResponseEntity<>(friends, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCatFriend(@RequestBody CatFriend catFriend) {
        catFriendService.addCatFriend(catFriend);
        return new ResponseEntity<>("Friend added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCatFriend(@PathVariable UUID id) {
        catFriendService.deleteCatFriend(id);
        return new ResponseEntity<>("Friend deleted successfully", HttpStatus.OK);
    }
}
