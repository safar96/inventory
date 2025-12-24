package uz.inventory.app.equipment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.inventory.app.core.payload.CustomApiResponse;
import uz.inventory.app.equipment.dto.EquipmentDto;
import uz.inventory.app.equipment.entity.EquipmentEntity;
import uz.inventory.app.equipment.service.EquipmentService;
import uz.inventory.app.util.dto.PaginationRequestDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @PostMapping("/list")
    public ResponseEntity<Page<EquipmentDto>> getAll(@RequestBody PaginationRequestDto paginationRequestDto) {
        Page<EquipmentDto> page = equipmentService.getAll(paginationRequestDto);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentEntity> getById(@PathVariable Long id) {
        return equipmentService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public CustomApiResponse create(@RequestBody EquipmentEntity entity) {
        try {
            EquipmentEntity saved = equipmentService.save(entity);
            return new CustomApiResponse("Equipment created successfully! ID: " + saved.getId(), true);
        } catch (Exception e) {
            return new CustomApiResponse("Failed to create Equipment: " + e.getMessage(), false);
        }
    }

    @PostMapping("/update")
    public CustomApiResponse update(@RequestBody EquipmentEntity entity) {
        try {
            Long id = entity.getId();
            EquipmentEntity updated = equipmentService.update(id, entity);
            return new CustomApiResponse("Equipment updated successfully! ID: " + updated.getId(), true);
        } catch (RuntimeException e) {
            return new CustomApiResponse("Failed to update Equipment: " + e.getMessage(), false);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody EquipmentEntity entity) {
        Long id = entity.getId();
        try {
            equipmentService.delete(id);
            return ResponseEntity.ok("Equipment successfully deleted");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }
}
