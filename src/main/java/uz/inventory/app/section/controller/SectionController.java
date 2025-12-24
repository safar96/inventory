package uz.inventory.app.section.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.inventory.app.core.payload.CustomApiResponse;
import uz.inventory.app.section.dto.SectionDto;
import uz.inventory.app.section.entity.SectionEntity;
import uz.inventory.app.section.service.SectionService;
import uz.inventory.app.util.dto.PaginationRequestDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/section")
public class SectionController {

    private final SectionService sectionService;

    @PostMapping("/list")
    public ResponseEntity<Page<SectionDto>> getAll(@RequestBody PaginationRequestDto paginationRequestDto) {
        Page<SectionDto> page = sectionService.getAll(paginationRequestDto);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectionEntity> getById(@PathVariable Long id) {
        return sectionService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public CustomApiResponse create(@RequestBody SectionEntity entity) {
        try {
            SectionEntity saved = sectionService.save(entity);
            return new CustomApiResponse("Section created successfully! ID: " + saved.getId(), true);
        } catch (Exception e) {
            return new CustomApiResponse("Failed to create Section: " + e.getMessage(), false);
        }
    }

    @PostMapping("/update")
    public CustomApiResponse update(@RequestBody SectionEntity entity) {
        try {
            Long id = entity.getId();
            SectionEntity updated = sectionService.update(id, entity);
            return new CustomApiResponse("Section updated successfully! ID: " + updated.getId(), true);
        } catch (RuntimeException e) {
            return new CustomApiResponse("Failed to update Section: " + e.getMessage(), false);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody SectionEntity entity) {
        Long id = entity.getId();
        try {
            sectionService.delete(id);
            return ResponseEntity.ok("Section successfully deleted");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }
}
