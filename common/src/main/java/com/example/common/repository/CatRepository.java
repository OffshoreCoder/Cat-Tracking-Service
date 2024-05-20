package com.example.common.repository;

import com.example.common.models.Cat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface CatRepository extends JpaRepository<Cat, UUID> {
    List<Cat> findAllByOwner_OwnerId(UUID ownerId);
    Page<Cat> findAllByOwner_OwnerId(Pageable pageable, UUID ownerId);
    List<Cat> findAllByColor(String color);
}
