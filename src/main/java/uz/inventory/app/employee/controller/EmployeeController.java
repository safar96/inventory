package uz.inventory.app.employee.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
}
