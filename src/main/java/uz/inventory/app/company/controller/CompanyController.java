package uz.inventory.app.company.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.inventory.app.company.dto.CompanyDto;
import uz.inventory.app.util.dto.PaginationRequestDto;
import uz.inventory.app.company.entity.CompanyEntity;
import uz.inventory.app.core.payload.CustomApiResponse;
import uz.inventory.app.company.service.CompanyService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/company")
public class CompanyController {

    final private CompanyService companyService;

    @PostMapping("/list")
    public ResponseEntity<Page<CompanyDto>> getCompanies(@RequestBody PaginationRequestDto paginationRequestDto) {
        Page<CompanyDto> companies = companyService.getCompanies(paginationRequestDto);
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyEntity> getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public CustomApiResponse createCompany(@RequestBody CompanyEntity companyEntity) {
        try {
            CompanyEntity savedCompany = companyService.saveCompany(companyEntity);
            return new CustomApiResponse("Company created successfully! ID: " + savedCompany.getId(), true);
        } catch (Exception e) {
            return new CustomApiResponse("Failed to create company: " + e.getMessage(), false);
        }
    }

    @PostMapping("/update")
    public CustomApiResponse updateCompany(@RequestBody CompanyEntity companyEntity) {
        try {
            Long id = companyEntity.getId();
            CompanyEntity updatedCompany = companyService.updateCompany(id, companyEntity);
            return new CustomApiResponse("Company updated successfully! ID: " + updatedCompany.getId(), true);
        } catch (RuntimeException e) {
            return new CustomApiResponse("Failed to update company: " + e.getMessage(), false);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteCompany(@RequestBody CompanyEntity companyEntity) {

        Long id = companyEntity.getId();
        try {
            String message = companyService.deleteCompany(id);
            return ResponseEntity.ok(message);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }
}
