package uz.inventory.app.controller.company;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.inventory.app.dto.company.CompanyDto;
import uz.inventory.app.dto.util.PaginationRequestDto;
import uz.inventory.app.entity.company.CompanyEntity;
import uz.inventory.app.payload.ApiResponse;
import uz.inventory.app.service.company.CompanyService;


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
    public ApiResponse createCompany(@RequestBody CompanyEntity companyEntity) {
        try {
            CompanyEntity savedCompany = companyService.saveCompany(companyEntity);
            return new ApiResponse("Company created successfully! ID: " + savedCompany.getId(), true);
        } catch (Exception e) {
            return new ApiResponse("Failed to create company: " + e.getMessage(), false);
        }
    }

    @PostMapping("/update")
    public ApiResponse updateCompany(@RequestBody CompanyEntity companyEntity) {
        try {
            Long id = companyEntity.getId();
            CompanyEntity updatedCompany = companyService.updateCompany(id, companyEntity);
            return new ApiResponse("Company updated successfully! ID: " + updatedCompany.getId(), true);
        } catch (RuntimeException e) {
            return new ApiResponse("Failed to update company: " + e.getMessage(), false);
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
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
