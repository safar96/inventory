package uz.inventory.app.controller.tariff;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.inventory.app.dto.tariff.CompanyTariffDto;
import uz.inventory.app.dto.tariff.TariffDto;
import uz.inventory.app.dto.util.IdDto;
import uz.inventory.app.dto.util.PaginationDto;
import uz.inventory.app.service.tariff.CompanyTariffService;
import uz.inventory.app.service.tariff.TariffService;

@RestController
@RequestMapping("/company-tariff")
@RequiredArgsConstructor
public class CompanyTariffController {
    private final CompanyTariffService companyTariffService;

    @PostMapping("/create")
    public ResponseEntity<?> createTariff(@RequestBody CompanyTariffDto companyTariffDto) {
        return companyTariffService.saveCompanyTariff(companyTariffDto);
    }
}
