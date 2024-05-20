package com.example.webMicroservice.services;

import com.example.common.models.CatFriend;
import com.example.common.repository.CatFriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CatFriendService {

    private final CatFriendRepository catFriendRepository;

    @Autowired
    public CatFriendService(CatFriendRepository catFriendRepository) {
        this.catFriendRepository = catFriendRepository;
    }

    public List<CatFriend> getFriendsOfCat(UUID catId) {
        return catFriendRepository.findAllByCat_CatId(catId);
    }

    public void addCatFriend(CatFriend catFriend) {
        catFriendRepository.save(catFriend);
    }

    public void deleteCatFriend(UUID id) {
        catFriendRepository.deleteById(id);
    }
}
