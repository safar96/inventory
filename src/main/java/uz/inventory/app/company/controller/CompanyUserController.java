package uz.inventory.app.company.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.inventory.app.company.dto.CompanyUserDto;
import uz.inventory.app.company.entity.CompanyUserEntity;
import uz.inventory.app.company.service.CompanyUserService;
import uz.inventory.app.core.payload.CustomApiResponse;
import uz.inventory.app.util.dto.PaginationRequestDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/company-user")
public class CompanyUserController {

    private final CompanyUserService companyUserService;

    @PostMapping("/list")
    public ResponseEntity<Page<CompanyUserDto>> getAll(@RequestBody PaginationRequestDto paginationRequestDto) {
        Page<CompanyUserDto> page = companyUserService.getAll(paginationRequestDto);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyUserEntity> getById(@PathVariable Long id) {
        return companyUserService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/assign")
    public CustomApiResponse assignUser(@RequestParam Long companyId, @RequestParam Long userId) {
        try {
            CompanyUserEntity saved = companyUserService.assignUser(companyId, userId);
            return new CustomApiResponse("Kompaniyaga user biriktirildi! ID: " + saved.getId(), true);
        } catch (Exception e) {
            return new CustomApiResponse("Xatolik: " + e.getMessage(), false);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody CompanyUserEntity entity) {
        Long id = entity.getId();
        try {
            companyUserService.delete(id);
            return ResponseEntity.ok("Kompaniyadan user ajratildi");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }
}
