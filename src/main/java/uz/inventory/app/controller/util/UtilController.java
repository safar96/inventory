package uz.inventory.app.controller.util;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.inventory.app.dto.util.IdDto;
import uz.inventory.app.service.UtilService;


@RestController
@RequestMapping("/util")
@RequiredArgsConstructor
public class UtilController {
    private final UtilService utilService;

    @GetMapping("/regions")
    public ResponseEntity<?> getRegions() {
        return utilService.getRegions();
    }

    @PostMapping("/districts")
    public ResponseEntity<?> getDistricts(@RequestBody IdDto idDto) {
        return utilService.getDistricts(idDto.getId());
    }

    @GetMapping("/gender")
    public ResponseEntity<?> getGender() {
        return utilService.getGenders();
    }
}
