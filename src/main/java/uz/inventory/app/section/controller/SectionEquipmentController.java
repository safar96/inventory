package uz.inventory.app.section.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.inventory.app.core.payload.CustomApiResponse;
import uz.inventory.app.section.dto.SectionEquipmentDto;
import uz.inventory.app.section.entity.SectionEquipmentEntity;
import uz.inventory.app.section.service.SectionEquipmentService;
import uz.inventory.app.util.dto.PaginationRequestDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/section-equipment")
public class SectionEquipmentController {

    private final SectionEquipmentService sectionEquipmentService;

    @PostMapping("/list")
    public ResponseEntity<Page<SectionEquipmentDto>> getAll(@RequestBody PaginationRequestDto paginationRequestDto) {
        Page<SectionEquipmentDto> page = sectionEquipmentService.getAll(paginationRequestDto);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectionEquipmentEntity> getById(@PathVariable Long id) {
        return sectionEquipmentService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/assign")
    public CustomApiResponse assign(@RequestParam Long sectionId, @RequestParam Long equipmentId) {
        try {
            SectionEquipmentEntity saved = sectionEquipmentService.assign(sectionId, equipmentId);
            return new CustomApiResponse("Bo'limga jihoz biriktirildi! ID: " + saved.getId(), true);
        } catch (Exception e) {
            return new CustomApiResponse("Xatolik: " + e.getMessage(), false);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody SectionEquipmentEntity entity) {
        Long id = entity.getId();
        try {
            sectionEquipmentService.delete(id);
            return ResponseEntity.ok("Bo'limdan jihoz ajratildi");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }
}
