package uz.inventory.app.equipment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.inventory.app.core.component.SearchSpecification;
import uz.inventory.app.equipment.dto.EquipmentDto;
import uz.inventory.app.equipment.entity.EquipmentEntity;
import uz.inventory.app.equipment.repository.EquipmentRepository;
import uz.inventory.app.util.dto.PaginationRequestDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public Page<EquipmentDto> getAll(PaginationRequestDto paginationRequestDto) {
        String searchTerm = paginationRequestDto.getSearch();
        String[] searchFields = {"name", "description", "serialNumber", "inventoryNumber"};
        Specification<EquipmentEntity> specification = SearchSpecification.containsSearchTerm(searchTerm, searchFields);

        Pageable pageable = PageRequest.of(
                paginationRequestDto.getPage(),
                paginationRequestDto.getSize(),
                Sort.by(Sort.Order.asc("name"))
        );

        Page<EquipmentEntity> page = equipmentRepository.findAll(specification, pageable);
        return page.map(this::toDto);
    }

    public Optional<EquipmentEntity> getById(Long id) {
        return equipmentRepository.findById(id);
    }

    public EquipmentEntity save(EquipmentEntity entity) {
        return equipmentRepository.save(entity);
    }

    public EquipmentEntity update(Long id, EquipmentEntity updatedEntity) {
        return equipmentRepository.findById(id).map(entity -> {
            entity.setName(updatedEntity.getName());
            entity.setDescription(updatedEntity.getDescription());
            entity.setSerialNumber(updatedEntity.getSerialNumber());
            entity.setInventoryNumber(updatedEntity.getInventoryNumber());
            entity.setEquipmentType(updatedEntity.getEquipmentType());
            entity.setCompany(updatedEntity.getCompany());
            entity.setState(updatedEntity.getState());
            entity.setImageId(updatedEntity.getImageId());
            return equipmentRepository.save(entity);
        }).orElseThrow(() -> new RuntimeException("Equipment not found with id " + id));
    }

    public void delete(Long id) {
        if (equipmentRepository.existsById(id)) {
            equipmentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Equipment not found with id " + id);
        }
    }

    private EquipmentDto toDto(EquipmentEntity entity) {
        EquipmentDto dto = new EquipmentDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setSerialNumber(entity.getSerialNumber());
        dto.setInventoryNumber(entity.getInventoryNumber());
        dto.setState(entity.getState());
        dto.setImageId(entity.getImageId());

        if (entity.getEquipmentType() != null) {
            dto.setEquipmentTypeId(entity.getEquipmentType().getId());
            dto.setEquipmentTypeName(entity.getEquipmentType().getName());
        }

        if (entity.getCompany() != null) {
            dto.setCompanyId(entity.getCompany().getId());
            dto.setCompanyName(entity.getCompany().getName());
        }

        return dto;
    }
}
