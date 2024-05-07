package classes;

import org.example.Entities.models.Owner;
import org.example.Entities.repository.OwnerRepository;
import org.example.Entities.dto.OwnerDto;
import org.example.Entities.dto.OwnerPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
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

    public Owner getOwnerById(UUID ownerId) {
        return ownerRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("Owner with current id does not exists"));
    }

    public Owner getOwnerByName(String name) {
        return ownerRepository.findByName(name);
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


    private OwnerDto mapOwnerToDto(Owner owner) {
        return OwnerDto.builder()
                .birthDate(owner.getBirthDate())
                .build();
    }
}
