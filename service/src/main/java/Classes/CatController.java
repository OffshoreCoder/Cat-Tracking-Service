package Classes;

import org.example.Entities.Cat;
import org.example.Entities.Owner;
import org.example.Entities.dto.CatDto;
import org.example.Entities.dto.CatPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cat/")
public class CatController {

    private final CatService catService;
    private final OwnerService ownerService;

    @Autowired
    public CatController(CatService catService, OwnerService ownerService) {
        this.catService = catService;
        this.ownerService = ownerService;
    }

    @PutMapping("update/{name}/{catId}")
    public ResponseEntity<String> updateCat(
            @RequestBody CatDto catDto, @PathVariable("name") String name,
            @PathVariable("catId") UUID catId
    ){

        catService.addOrUpdateCatWithDtoById(catDto, catId);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("add/{name}/{catId}")
    public ResponseEntity<String> addCat(
            @RequestBody CatDto catDto, @PathVariable("name") String name,
            @PathVariable("catId") UUID catId
    ){

        catService.addOrUpdateCatWithDtoById(catDto, catId);
        return new ResponseEntity<>("Cat was updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("delete/{name}/{catId}")
    public ResponseEntity<String> addOwner(
            @PathVariable("name") String name, @PathVariable("catId") UUID catId
    ){
        Cat cat = catService.getCatById(catId);
        catService.deleteCat(cat);
        return new ResponseEntity<>("Cat was updated successfully", HttpStatus.OK);
    }

    @GetMapping("details/{name}/{catId}")
    public ResponseEntity<List<Cat>> getCatDetails(
            @PathVariable("name") String name, @PathVariable("catId") UUID catId
    ){

        Owner owner = ownerService.getOwnerByName(name);
        return new ResponseEntity<>(catService.getCatsByOwnerId(owner.getId()), HttpStatus.OK);
    }

    @GetMapping("all/{name}")
    public ResponseEntity<CatPageResponse> getAllUsersDetails(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @PathVariable("name") String name
    ) {

        Owner owner = ownerService.getOwnerByName(name);
        return new ResponseEntity<>(catService.getAllCatDtoWithPagination(pageNumber, pageSize, owner.getId()), HttpStatus.OK);
    }
}