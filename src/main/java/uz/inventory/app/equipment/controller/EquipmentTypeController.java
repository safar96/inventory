package uz.inventory.app.equipment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.inventory.app.core.payload.CustomApiResponse;
import uz.inventory.app.equipment.dto.EquipmentTypeDto;
import uz.inventory.app.equipment.entity.EquipmentTypeEntity;
import uz.inventory.app.equipment.service.EquipmentTypeService;
import uz.inventory.app.util.dto.PaginationRequestDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/equipment-type")
public class EquipmentTypeController {

    private final EquipmentTypeService equipmentTypeService;

    @PostMapping("/list")
    public ResponseEntity<Page<EquipmentTypeDto>> getAll(@RequestBody PaginationRequestDto paginationRequestDto) {
        Page<EquipmentTypeDto> page = equipmentTypeService.getAll(paginationRequestDto);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentTypeEntity> getById(@PathVariable Long id) {
        return equipmentTypeService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public CustomApiResponse create(@RequestBody EquipmentTypeEntity entity) {
        try {
            EquipmentTypeEntity saved = equipmentTypeService.save(entity);
            return new CustomApiResponse("Equipment Type created successfully! ID: " + saved.getId(), true);
        } catch (Exception e) {
            return new CustomApiResponse("Failed to create Equipment Type: " + e.getMessage(), false);
        }
    }

    @PostMapping("/update")
    public CustomApiResponse update(@RequestBody EquipmentTypeEntity entity) {
        try {
            Long id = entity.getId();
            EquipmentTypeEntity updated = equipmentTypeService.update(id, entity);
            return new CustomApiResponse("Equipment Type updated successfully! ID: " + updated.getId(), true);
        } catch (RuntimeException e) {
            return new CustomApiResponse("Failed to update Equipment Type: " + e.getMessage(), false);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody EquipmentTypeEntity entity) {
        Long id = entity.getId();
        try {
            equipmentTypeService.delete(id);
            return ResponseEntity.ok("Equipment Type successfully deleted");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }
}
