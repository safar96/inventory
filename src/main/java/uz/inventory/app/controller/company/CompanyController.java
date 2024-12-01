package uz.inventory.app.controller.company;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.inventory.app.dto.company.CompanyDto;
import uz.inventory.app.dto.company.CompanyTariffDto;
import uz.inventory.app.dto.tariff.TariffDto;
import uz.inventory.app.dto.util.PaginationRequestDto;
import uz.inventory.app.entity.company.CompanyEntity;
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
    public CompanyEntity createCompany(@RequestBody CompanyEntity companyEntity) {
        return companyService.saveCompany(companyEntity);
    }

    @PostMapping("/update")
    public ResponseEntity<CompanyEntity> updateCompany(@RequestBody CompanyEntity companyEntity) {
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

    @PostMapping("/save-tariff")
    public ResponseEntity<?> saveTariff(@RequestBody CompanyTariffDto companyTariffDto) {
        return companyService.setCompanyTariff(companyTariffDto);
    }
}
