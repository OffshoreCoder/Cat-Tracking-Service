package com.example.common.repository;

import com.example.common.models.Owner;
import com.example.common.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, UUID> {
    Owner findOwnerByName(String name);
    @Query("SELECT r FROM Role r JOIN CatOwnerUser u ON u.id = r.id WHERE u.owner.ownerId = :ownerId")
    List<Role> findRolesByOwnerId(UUID ownerId);
}
