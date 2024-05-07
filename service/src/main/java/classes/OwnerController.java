package classes;

import org.example.Entities.models.Owner;
import org.example.Entities.dto.OwnerPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner/")
public class OwnerController {
    private final OwnerService ownerService;
    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @DeleteMapping("delete/{name}")
    public ResponseEntity<String> deleteOwner(@PathVariable("name") String name){

        Owner owner = ownerService.getOwnerByName(name);
        ownerService.deleteOwner(owner);
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }

    @GetMapping("details/{name}")
    public ResponseEntity<Owner> getOwnerDetails(@PathVariable("name") String name){
        return new ResponseEntity<>(ownerService.getOwnerByName(name), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<OwnerPageResponse> getAllUsersDetails(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {

        return new ResponseEntity<>(ownerService.getAllOwnerDtoWithPagination(pageNumber, pageSize), HttpStatus.OK);
    }
}