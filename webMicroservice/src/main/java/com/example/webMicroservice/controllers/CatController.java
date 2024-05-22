package com.example.webMicroservice.controllers;

import com.example.common.dto.CatDto;
import com.example.webMicroservice.services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cats")
public class CatController {

    @Autowired
    private CatService catService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")
    public ResponseEntity<List<CatDto>> getAllCats() {
        List<CatDto> cats = catService.getAllCats();
        return ResponseEntity.ok(cats);
    }

    @GetMapping("/{catId}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isOwner(authentication, #catId)")
    public ResponseEntity<CatDto> getCatById(@PathVariable UUID catId) {
        CatDto cat = catService.getCatById(catId);
        if (cat != null) {
            return ResponseEntity.ok(cat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/owner/{ownerId}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isOwner(authentication, #ownerId)")
    public ResponseEntity<List<CatDto>> getCatsByOwner(@PathVariable UUID ownerId) {
        List<CatDto> cats = catService.getCatsByOwner(ownerId);
        return ResponseEntity.ok(cats);
    }

    @GetMapping("/owner/{ownerId}/paged")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isOwner(authentication, #ownerId)")
    public ResponseEntity<Page<CatDto>> getCatsByOwner(Pageable pageable, @PathVariable UUID ownerId) {
        Page<CatDto> cats = catService.getCatsByOwner(pageable, ownerId);
        return ResponseEntity.ok(cats);
    }

    @GetMapping("/color/{color}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")
    public ResponseEntity<List<CatDto>> getCatsByColor(@PathVariable String color) {
        List<CatDto> cats = catService.getCatsByColor(color);
        return ResponseEntity.ok(cats);
    }
}
