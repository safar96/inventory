package uz.inventory.app.employee.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.inventory.app.core.payload.CustomApiResponse;
import uz.inventory.app.employee.dto.EmployeeDto;
import uz.inventory.app.employee.service.EmployeeService;

@RestController
@RequestMapping("/company/employee")
@RequiredArgsConstructor
public class EmployeeController {
    final private EmployeeService employeeService;


    @GetMapping("/list")
    public ResponseEntity<?> getEmployeeList(@RequestParam Long companyId,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(employeeService.getEmployeeList(companyId, page, pageSize));
    }

    @PostMapping("/create")
    public CustomApiResponse create(@RequestBody EmployeeDto employeeDto) {
        try {
            var saved = employeeService.addEmployee(employeeDto);
            return new CustomApiResponse("Xodim muvaffaqiyatli yaratildi! ID: " + saved.getId(), true);
        } catch (Exception e) {
            return new CustomApiResponse("Xodim yaratishda xatolik: " + e.getMessage(), false);
        }
    }
}
