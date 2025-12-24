package uz.inventory.app.section.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.inventory.app.core.component.SearchSpecification;
import uz.inventory.app.section.dto.SectionDto;
import uz.inventory.app.section.entity.SectionEntity;
import uz.inventory.app.section.repository.SectionRepository;
import uz.inventory.app.util.dto.PaginationRequestDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SectionService {

    private final SectionRepository sectionRepository;

    public Page<SectionDto> getAll(PaginationRequestDto paginationRequestDto) {
        String searchTerm = paginationRequestDto.getSearch();
        String[] searchFields = {"name", "description"};
        Specification<SectionEntity> specification = SearchSpecification.containsSearchTerm(searchTerm, searchFields);

        Pageable pageable = PageRequest.of(
                paginationRequestDto.getPage(),
                paginationRequestDto.getSize(),
                Sort.by(Sort.Order.asc("name"))
        );

        Page<SectionEntity> page = sectionRepository.findAll(specification, pageable);
        return page.map(this::toDto);
    }

    public Optional<SectionEntity> getById(Long id) {
        return sectionRepository.findById(id);
    }

    public SectionEntity save(SectionEntity entity) {
        return sectionRepository.save(entity);
    }

    public SectionEntity update(Long id, SectionEntity updatedEntity) {
        return sectionRepository.findById(id).map(entity -> {
            entity.setName(updatedEntity.getName());
            entity.setDescription(updatedEntity.getDescription());
            entity.setCompany(updatedEntity.getCompany());
            entity.setState(updatedEntity.getState());
            return sectionRepository.save(entity);
        }).orElseThrow(() -> new RuntimeException("Section not found with id " + id));
    }

    public void delete(Long id) {
        if (sectionRepository.existsById(id)) {
            sectionRepository.deleteById(id);
        } else {
            throw new RuntimeException("Section not found with id " + id);
        }
    }

    private SectionDto toDto(SectionEntity entity) {
        SectionDto dto = new SectionDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setState(entity.getState());

        if (entity.getCompany() != null) {
            dto.setCompanyId(entity.getCompany().getId());
            dto.setCompanyName(entity.getCompany().getName());
        }

        return dto;
    }
}
