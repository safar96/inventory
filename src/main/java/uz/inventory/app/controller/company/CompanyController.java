package uz.inventory.app.controller.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.inventory.app.dto.company.PaginationRequestDto;
import uz.inventory.app.entity.company.CompanyEntity;
import uz.inventory.app.service.company.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/list")
    public Page<CompanyEntity> getPaginatedAndSortedCompanies(@RequestBody PaginationRequestDto paginationRequestDto) {
        return companyService.getCompanies(paginationRequestDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyEntity> getCompanyById(@PathVariable Long id) {
        System.out.println(id);
        return companyService.getCompanyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/createCompany")
    public CompanyEntity createCompany(@RequestBody CompanyEntity companyEntity) {
        companyEntity.setCrOn(java.time.LocalDate.now());
        return companyService.saveCompany(companyEntity);
    }

    @PostMapping("/update")
    public ResponseEntity<CompanyEntity> updateCompany( @RequestBody CompanyEntity companyEntity) {
        try {
            Long id = companyEntity.getId();
            return ResponseEntity.ok(companyService.updateCompany(id, companyEntity));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteCompany(@RequestBody CompanyEntity companyEntity) {
        System.out.println(companyEntity);
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
