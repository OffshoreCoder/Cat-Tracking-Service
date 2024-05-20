package com.example.common.repository;

import com.example.common.models.CatFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface CatFriendRepository extends JpaRepository<CatFriend, UUID> {
    List<CatFriend> findAllByCat_CatId(UUID catId);
}
