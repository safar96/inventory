package uz.inventory.app.section.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.inventory.app.equipment.entity.EquipmentEntity;
import uz.inventory.app.equipment.repository.EquipmentRepository;
import uz.inventory.app.section.dto.SectionEquipmentDto;
import uz.inventory.app.section.entity.SectionEntity;
import uz.inventory.app.section.entity.SectionEquipmentEntity;
import uz.inventory.app.section.repository.SectionEquipmentRepository;
import uz.inventory.app.section.repository.SectionRepository;
import uz.inventory.app.util.dto.PaginationRequestDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SectionEquipmentService {

    private final SectionEquipmentRepository sectionEquipmentRepository;
    private final SectionRepository sectionRepository;
    private final EquipmentRepository equipmentRepository;

    public Page<SectionEquipmentDto> getAll(PaginationRequestDto paginationRequestDto) {
        Pageable pageable = PageRequest.of(
                paginationRequestDto.getPage(),
                paginationRequestDto.getSize(),
                Sort.by(Sort.Order.desc("id"))
        );

        Page<SectionEquipmentEntity> page = sectionEquipmentRepository.findAll(pageable);
        return page.map(this::toDto);
    }

    public Optional<SectionEquipmentEntity> getById(Long id) {
        return sectionEquipmentRepository.findById(id);
    }

    public SectionEquipmentEntity assign(Long sectionId, Long equipmentId) {
        SectionEntity section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new RuntimeException("Section not found with id " + sectionId));

        EquipmentEntity equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new RuntimeException("Equipment not found with id " + equipmentId));

        SectionEquipmentEntity entity = new SectionEquipmentEntity();
        entity.setSection(section);
        entity.setEquipment(equipment);
        entity.setState(1);

        return sectionEquipmentRepository.save(entity);
    }

    public void delete(Long id) {
        if (sectionEquipmentRepository.existsById(id)) {
            sectionEquipmentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Section Equipment not found with id " + id);
        }
    }

    private SectionEquipmentDto toDto(SectionEquipmentEntity entity) {
        SectionEquipmentDto dto = new SectionEquipmentDto();
        dto.setId(entity.getId());
        dto.setState(entity.getState());

        if (entity.getSection() != null) {
            dto.setSectionId(entity.getSection().getId());
            dto.setSectionName(entity.getSection().getName());
        }

        if (entity.getEquipment() != null) {
            dto.setEquipmentId(entity.getEquipment().getId());
            dto.setEquipmentName(entity.getEquipment().getName());
        }

        return dto;
    }
}
