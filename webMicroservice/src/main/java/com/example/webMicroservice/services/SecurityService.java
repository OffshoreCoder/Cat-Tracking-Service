package com.example.webMicroservice.services;

import com.example.common.repository.OwnerRepository;
import com.example.common.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SecurityService {

    private final OwnerRepository ownerRepository;
    private final UserRepository userRepository;

    @Autowired
    public SecurityService(OwnerRepository ownerRepository, UserRepository userRepository) {
        this.ownerRepository = ownerRepository;
        this.userRepository = userRepository;
    }

    public boolean hasAccessToOwner(UUID ownerId, Authentication authentication) {
        try {
            String username = authentication.getName();
            var user = userRepository.findByUsername(username);

            if (user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"))) {
                return true;
            }

            return ownerRepository.findById(ownerId)
                    .map(owner -> user.getOwner().getOwnerId().equals(owner.getOwnerId()))
                    .orElse(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean hasAccessToCat(UUID ownerId, UUID catId, Authentication authentication) {
        try {
            String username = authentication.getName();
            var user = userRepository.findByUsername(username);

            if (user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"))) {
                return true;
            }

            return ownerRepository.findById(ownerId)
                    .map(owner -> owner.getCats().stream().anyMatch(cat -> cat.getCatId().equals(catId)))
                    .orElse(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

