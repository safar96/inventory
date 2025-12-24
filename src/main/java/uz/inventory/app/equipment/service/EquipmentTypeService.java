package uz.inventory.app.equipment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.inventory.app.core.component.SearchSpecification;
import uz.inventory.app.equipment.dto.EquipmentTypeDto;
import uz.inventory.app.equipment.entity.EquipmentTypeEntity;
import uz.inventory.app.equipment.repository.EquipmentTypeRepository;
import uz.inventory.app.util.dto.PaginationRequestDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipmentTypeService {

    private final EquipmentTypeRepository equipmentTypeRepository;

    public Page<EquipmentTypeDto> getAll(PaginationRequestDto paginationRequestDto) {
        String searchTerm = paginationRequestDto.getSearch();
        String[] searchFields = {"name", "description"};
        Specification<EquipmentTypeEntity> specification = SearchSpecification.containsSearchTerm(searchTerm, searchFields);

        Pageable pageable = PageRequest.of(
                paginationRequestDto.getPage(),
                paginationRequestDto.getSize(),
                Sort.by(Sort.Order.asc("name"))
        );

        Page<EquipmentTypeEntity> page = equipmentTypeRepository.findAll(specification, pageable);
        return page.map(this::toDto);
    }

    public Optional<EquipmentTypeEntity> getById(Long id) {
        return equipmentTypeRepository.findById(id);
    }

    public EquipmentTypeEntity save(EquipmentTypeEntity entity) {
        return equipmentTypeRepository.save(entity);
    }

    public EquipmentTypeEntity update(Long id, EquipmentTypeEntity updatedEntity) {
        return equipmentTypeRepository.findById(id).map(entity -> {
            entity.setName(updatedEntity.getName());
            entity.setDescription(updatedEntity.getDescription());
            entity.setState(updatedEntity.getState());
            return equipmentTypeRepository.save(entity);
        }).orElseThrow(() -> new RuntimeException("Equipment Type not found with id " + id));
    }

    public void delete(Long id) {
        if (equipmentTypeRepository.existsById(id)) {
            equipmentTypeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Equipment Type not found with id " + id);
        }
    }

    private EquipmentTypeDto toDto(EquipmentTypeEntity entity) {
        EquipmentTypeDto dto = new EquipmentTypeDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setState(entity.getState());
        return dto;
    }
}
