package uz.inventory.app.employee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.inventory.app.core.payload.CustomApiResponse;
import uz.inventory.app.employee.dto.EmployeeEquipmentDto;
import uz.inventory.app.employee.entity.EmployeeEquipmentEntity;
import uz.inventory.app.employee.service.EmployeeEquipmentService;
import uz.inventory.app.util.dto.PaginationRequestDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employee-equipment")
public class EmployeeEquipmentController {

    private final EmployeeEquipmentService employeeEquipmentService;

    @PostMapping("/list")
    public ResponseEntity<Page<EmployeeEquipmentDto>> getAll(@RequestBody PaginationRequestDto paginationRequestDto) {
        Page<EmployeeEquipmentDto> page = employeeEquipmentService.getAll(paginationRequestDto);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEquipmentEntity> getById(@PathVariable Long id) {
        return employeeEquipmentService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/assign")
    public CustomApiResponse assignEquipment(@RequestParam Long employeeId, @RequestParam Long equipmentId) {
        try {
            EmployeeEquipmentEntity saved = employeeEquipmentService.assignEquipment(employeeId, equipmentId);
            return new CustomApiResponse("Xodimga jihoz biriktirildi! ID: " + saved.getId(), true);
        } catch (Exception e) {
            return new CustomApiResponse("Xatolik: " + e.getMessage(), false);
        }
    }

    @PostMapping("/return/{id}")
    public CustomApiResponse returnEquipment(@PathVariable Long id) {
        try {
            EmployeeEquipmentEntity updated = employeeEquipmentService.returnEquipment(id);
            return new CustomApiResponse("Jihoz qaytarildi! ID: " + updated.getId(), true);
        } catch (Exception e) {
            return new CustomApiResponse("Xatolik: " + e.getMessage(), false);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody EmployeeEquipmentEntity entity) {
        Long id = entity.getId();
        try {
            employeeEquipmentService.delete(id);
            return ResponseEntity.ok("Xodimdan jihoz ajratildi");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }
}
