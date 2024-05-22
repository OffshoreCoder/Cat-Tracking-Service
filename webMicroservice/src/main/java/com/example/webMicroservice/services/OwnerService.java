package com.example.webMicroservice.services;

import com.example.common.dto.CatDto;
import com.example.common.dto.OwnerDto;
import com.example.common.dto.OwnerPageResponse;
import com.example.common.models.Cat;
import com.example.common.models.Owner;
import com.example.common.repository.CatRepository;
import com.example.common.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final CatRepository catRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository, CatRepository catRepository) {
        this.ownerRepository = ownerRepository;
        this.catRepository = catRepository;
    }

    public void updateOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    public void addOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    public void deleteOwner(Owner owner) {
        ownerRepository.delete(owner);
    }

    public Owner getOwnerById(UUID id) {
        return ownerRepository.findById(id).orElse(null);
    }

    public Owner getOwnerByName(String name) {
        return ownerRepository.findOwnerByName(name);
    }

    public OwnerPageResponse getAllOwnerDtoWithPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Owner> pageOfOwners = ownerRepository.findAll(pageable);

        List<Owner> ownersContent = pageOfOwners.getContent();
        List<OwnerDto> ownersDtoContent = ownersContent.stream().map(this::mapOwnerToDto).toList();

        return OwnerPageResponse.builder()
                .content(ownersDtoContent)
                .pageNumber(pageOfOwners.getNumber())
                .pageSize(pageOfOwners.getSize())
                .totalElement(pageOfOwners.getTotalElements())
                .totalPages(pageOfOwners.getTotalPages())
                .isLast(pageOfOwners.isLast())
                .build();
    }

    public List<CatDto> getCatsByOwnerId(UUID ownerId) {
        List<Cat> cats = catRepository.findAllByOwner_OwnerId(ownerId);
        cats.forEach(cat -> System.out.println("Cat Name: " + cat.getName()));
        return cats.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private OwnerDto mapOwnerToDto(Owner owner) {
        return OwnerDto.builder()
                .ownerId(owner.getOwnerId())
                .name(owner.getName())
                .birthDate(owner.getBirthDate())
                .build();
    }

    private CatDto convertToDto(Cat cat) {
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
