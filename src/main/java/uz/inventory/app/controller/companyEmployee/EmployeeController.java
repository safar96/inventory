package uz.inventory.app.controller.companyEmployee;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.inventory.app.dto.companyEmployee.EmployeeDto;
import uz.inventory.app.dto.util.PaginationRequestDto;
import uz.inventory.app.entity.companyEmployee.EmployeeEntity;
import uz.inventory.app.payload.ApiResponse;
import uz.inventory.app.service.companyEmployee.EmployeeService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/company/employee")
public class EmployeeController {
    final private EmployeeService employeeService;

    @PostMapping("/list")
    public ResponseEntity<Page<EmployeeDto>> getEmployees(@RequestBody PaginationRequestDto paginationRequestDto) {
        Page<EmployeeDto> employees = employeeService.getEmployee(paginationRequestDto);
        return ResponseEntity.ok(employees);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/create")
    public ApiResponse createEmployee(@RequestBody EmployeeEntity employeeEntity) {
        try {
            EmployeeEntity savedEmployee = employeeService.saveEmployee(employeeEntity);
            return new ApiResponse("Employee created successfully! ID: " + savedEmployee.getId(), true);
        } catch (Exception e) {
            return new ApiResponse("Failed to create Employee: " + e.getMessage(), false);
        }
    }

    @PostMapping("/update")
    public ApiResponse updateCompany(@RequestBody EmployeeEntity employeeEntity) {
        try {
            Long id = employeeEntity.getId();
            EmployeeEntity updatedEmployee = employeeService.updateEmployee(id, employeeEntity);
            return new ApiResponse("Employee updated successfully! ID: " + updatedEmployee.getId(), true);
        } catch (RuntimeException e) {
            return new ApiResponse("Failed to update employee: " + e.getMessage(), false);
        }
    }

//    @PostMapping("/delete")
//    public ResponseEntity<String> deleteCompany(@RequestBody EmployeeEntity companyEntity) {
//
//        Long id = EmployeeEntity.getId();
//        try {
//            String message = employeeService.deleteCompany(id);
//            return ResponseEntity.ok(message);
//        } catch (RuntimeException ex) {
//            return ResponseEntity.status(404).body(ex.getMessage());
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
