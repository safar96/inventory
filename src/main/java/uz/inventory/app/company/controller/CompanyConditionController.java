package uz.inventory.app.company.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.inventory.app.company.entity.CompanyConditionEntity;
import uz.inventory.app.company.service.CompanyConditionService;
import uz.inventory.app.core.payload.CustomApiResponse;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/company-condition")
public class CompanyConditionController {

    private final CompanyConditionService companyConditionService;

    @GetMapping("/list")
    public ResponseEntity<List<CompanyConditionEntity>> getAll() {
        List<CompanyConditionEntity> list = companyConditionService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyConditionEntity> getById(@PathVariable Long id) {
        return companyConditionService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public CustomApiResponse create(@RequestBody CompanyConditionEntity entity) {
        try {
            CompanyConditionEntity saved = companyConditionService.save(entity);
            return new CustomApiResponse("Kompaniya holati yaratildi! ID: " + saved.getId(), true);
        } catch (Exception e) {
            return new CustomApiResponse("Xatolik: " + e.getMessage(), false);
        }
    }

    @PostMapping("/update")
    public CustomApiResponse update(@RequestBody CompanyConditionEntity entity) {
        try {
            Long id = entity.getId();
            CompanyConditionEntity updated = companyConditionService.update(id, entity);
            return new CustomApiResponse("Kompaniya holati yangilandi! ID: " + updated.getId(), true);
        } catch (RuntimeException e) {
            return new CustomApiResponse("Xatolik: " + e.getMessage(), false);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody CompanyConditionEntity entity) {
        Long id = entity.getId();
        try {
            companyConditionService.delete(id);
            return ResponseEntity.ok("Kompaniya holati o'chirildi");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }
}
