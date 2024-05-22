package com.example.webMicroservice.controllers;

import com.example.common.dto.CatDto;
import com.example.common.dto.OwnerPageResponse;
import com.example.common.models.Owner;
import com.example.webMicroservice.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.hasAccessToOwner(#id, authentication)")
    public ResponseEntity<String> deleteOwner(@PathVariable("id") UUID id) {
        Owner owner = ownerService.getOwnerById(id);
        ownerService.deleteOwner(owner);
        return new ResponseEntity<>("Owner deleted successfully", HttpStatus.OK);
    }

    @GetMapping("details/{id}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.hasAccessToOwner(#id, authentication)")
    public ResponseEntity<Owner> getOwnerDetails(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(ownerService.getOwnerById(id), HttpStatus.OK);
    }

    @GetMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OwnerPageResponse> getAllUsersDetails(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return new ResponseEntity<>(ownerService.getAllOwnerDtoWithPagination(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/{ownerId}/cats")
    @PreAuthorize("hasRole('ADMIN') or @securityService.hasAccessToOwner(#ownerId, authentication)")
    public ResponseEntity<List<CatDto>> getCatsByOwnerId(@PathVariable("ownerId") UUID ownerId) {
        List<CatDto> cats = ownerService.getCatsByOwnerId(ownerId);
        return ResponseEntity.ok(cats);
    }
}
