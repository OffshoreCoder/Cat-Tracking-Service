package com.example.webMicroservice.services;

import com.example.common.dto.CatDto;
import com.example.common.models.Cat;
import com.example.common.repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CatService {

    @Autowired
    private CatRepository catRepository;

    public List<CatDto> getAllCats() {
        List<Cat> cats = catRepository.findAll();
        return cats.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public CatDto getCatById(UUID catId) {
        Optional<Cat> cat = catRepository.findById(catId);
        return cat.map(this::convertToDto).orElse(null);
    }

    public List<CatDto> getCatsByOwner(UUID ownerId) {
        List<Cat> cats = catRepository.findAllByOwner_OwnerId(ownerId);
        return cats.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Page<CatDto> getCatsByOwner(Pageable pageable, UUID ownerId) {
        Page<Cat> cats = catRepository.findAllByOwner_OwnerId(pageable, ownerId);
        return cats.map(this::convertToDto);
    }

    public List<CatDto> getCatsByColor(String color) {
        List<Cat> cats = catRepository.findAllByColor(color);
        return cats.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public CatDto convertToDto(Cat cat) {
        CatDto dto = new CatDto();
        dto.setCatId(cat.getCatId());
        dto.setName(cat.getName());
        dto.setBreed(cat.getBreed());
        dto.setColor(cat.getColor());
        dto.setBirthDate(cat.getBirthDate());
        dto.setOwnerId(cat.getOwner().getOwnerId());
        dto.setFriends(cat.getFriends().stream().map(Cat::getCatId).collect(Collectors.toSet()));
        return dto;
    }
}
