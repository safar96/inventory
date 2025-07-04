package uz.inventory.app.controller.companyEmployee;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.inventory.app.service.companyEmployee.EmployeeService;

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
}
