package com.example.common.repository;

import com.example.common.models.CatOwnerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<CatOwnerUser, UUID> {
    CatOwnerUser findByUsername(String username);
    boolean existsByUsername(String username);
}
