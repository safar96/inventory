package uz.inventory.app.controller.companyEmployee;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.inventory.app.dto.util.PaginationRequestDto;
import uz.inventory.app.entity.company.CompanyEntity;
import uz.inventory.app.service.companyEmployee.EmployeeService;

@RestController
@RequestMapping("/company/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping("/list")
    public Page<CompanyEntity> getPaginatedAndSortedCompanies(@RequestBody PaginationRequestDto paginationRequestDto) {
        return null;
//        return employeeService.getEmployee(paginationRequestDto);
    }
}
