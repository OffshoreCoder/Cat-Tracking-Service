package Classes;

import org.example.Entities.Cat;
import org.example.Entities.CatRepository;
import org.example.Entities.dto.CatDto;
import org.example.Entities.dto.CatPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CatService {

    private final CatRepository catRepository;

    @Autowired
    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }
    
    public Cat getCatById(UUID catId) {
        return catRepository.findById(catId).orElseThrow(() -> new RuntimeException("Cat with current id does not exists"));
    }

    public Cat getCatByName(String catName) {
        return catRepository.findCatByName(catName);
    }

    public List<Cat> getCatsByOwnerId(UUID ownerId) {
        return catRepository.findAllByOwnerId(ownerId);
    }
    
    public List<Cat> getAllCats() {
        return catRepository.findAll();
    }
    
    public void addCat(Cat cat) {
        catRepository.save(cat);
    }

    public void updateCat(Cat cat) {
        catRepository.save(cat);
    }
    
    public void deleteCat(Cat cat) {
        catRepository.delete(cat);
    }
    
    public void addOrUpdateCatWithDtoById(CatDto catDto, UUID id) {
        Cat cat = catRepository.findById(id).orElseThrow(() -> new RuntimeException("Cat with current id does not exists"));

        Cat catResult = new Cat();
        catResult.setName(catDto.getName());
        catResult.setBreed(catDto.getBreed());
        catResult.setBirthDate(catDto.getBirthDate());
        catResult.setColor(catDto.getColor());

        catResult.setOwner(cat.getOwner());
        catResult.setId(cat.getId());

        catRepository.save(catResult);
    }

    public CatPageResponse getAllCatDtoWithPagination(int pageNumber, int pageSize, UUID ownerId) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Cat> pageOfOwners = catRepository.findAllByOwnerId(pageable, ownerId);

        List<Cat> ownersContent = pageOfOwners.getContent();
        List<CatDto> ownersDtoContent = ownersContent.stream().map(this::mapCatToDto).toList();

        return CatPageResponse.builder()
                .content(ownersDtoContent)
                .pageNumber(pageOfOwners.getNumber())
                .pageSize(pageOfOwners.getSize())
                .totalElement(pageOfOwners.getTotalElements())
                .totalPages(pageOfOwners.getTotalPages())
                .isLast(pageOfOwners.isLast())
                .build();
    }

    private CatDto mapCatToDto(Cat cat) {
        return CatDto.builder()
                .color(cat.getColor())
                .breed(cat.getBreed())
                .name(cat.getName())
                .birthDate(cat.getBirthDate())
                .build();
    }
}
