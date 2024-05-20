package com.example.webMicroservice.controllers;

import com.example.common.dto.CatDto;
import com.example.common.models.Cat;
import com.example.webMicroservice.services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cats")
public class CatController {

    @Autowired
    private CatService catService;

    @GetMapping
    public ResponseEntity<List<CatDto>> getAllCats() {
        List<CatDto> cats = catService.getAllCats();
        return ResponseEntity.ok(cats);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CatDto> getCatById(@PathVariable UUID catId) {
        CatDto cat = catService.getCatById(catId);
        if (cat != null) {
            return ResponseEntity.ok(cat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<CatDto>> getCatsByOwner(@PathVariable UUID ownerId) {
        List<CatDto> cats = catService.getCatsByOwner(ownerId);
        return ResponseEntity.ok(cats);
    }

    @GetMapping("/owner/{ownerId}/paged")
    public ResponseEntity<Page<CatDto>> getCatsByOwner(Pageable pageable, @PathVariable UUID ownerId) {
        Page<CatDto> cats = catService.getCatsByOwner(pageable, ownerId);
        return ResponseEntity.ok(cats);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<CatDto>> getCatsByColor(@PathVariable String color) {
        List<CatDto> cats = catService.getCatsByColor(color);
        return ResponseEntity.ok(cats);
    }
}
