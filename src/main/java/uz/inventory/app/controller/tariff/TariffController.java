package uz.inventory.app.controller.tariff;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.inventory.app.dto.tariff.TariffDto;
import uz.inventory.app.dto.util.IdDto;
import uz.inventory.app.dto.util.PaginationDto;
import uz.inventory.app.service.tariff.TariffService;

@RestController
@RequestMapping("/tariff")
@RequiredArgsConstructor
public class TariffController {
    private final TariffService tariffService;


    @PostMapping("/list")
    public Page<TariffDto> tariffDtoPage(@RequestBody PaginationDto paginationDto) {
        return tariffService.getTariffs(paginationDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TariffDto> getCompanyById(@PathVariable Long id) {
        return tariffService.getTariff(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTariff(@RequestBody TariffDto tariffDto) {
        return tariffService.saveTariff(tariffDto);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateTariff(@RequestBody TariffDto tariffDto) {
        return tariffService.updateTariff(tariffDto);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteTariff(@RequestBody IdDto id) {
        return tariffService.deleteTariff(id.getId());
    }
}
