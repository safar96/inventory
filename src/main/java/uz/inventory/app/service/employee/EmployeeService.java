package uz.inventory.app.service.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.inventory.app.dto.companyEmployee.EmployeeDto;
import uz.inventory.app.entity.company.CompanyEntity;
import uz.inventory.app.entity.employee.EmployeeEntity;
import uz.inventory.app.repository.employee.EmployeeRepository;
import uz.inventory.app.repository.company.CompanyRepository;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    final private EmployeeRepository employeeRepository;
    final private CompanyRepository companyRepository;


    public Page<EmployeeEntity> getEmployeeList(Long companyId,int page,int sizeSize) {
        Pageable pageable = PageRequest.of(page, sizeSize, Sort.by(Sort.Order.asc("name")));
        return employeeRepository.findAllByCompany_Id(companyId, pageable);
    }




    public EmployeeEntity addEmployee(EmployeeDto employeeRequestDto) {
        CompanyEntity company = companyRepository.findById(employeeRequestDto.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found with id " + employeeRequestDto.getCompanyId()));

        EmployeeEntity employee = new EmployeeEntity();
        employee.setFirstName(employeeRequestDto.getFirstName());
        employee.setMiddleName(employeeRequestDto.getMiddleName());
        employee.setLastName(employeeRequestDto.getLastName());
        employee.setGenderCode(employeeRequestDto.getGenderCode());
        employee.setBirthDate(employeeRequestDto.getBirthDate());
        employee.setSectionId(employeeRequestDto.getSectionId());
        employee.setState(employeeRequestDto.getState());
        employee.setCompany(company); // Set the company relationship

        return employeeRepository.save(employee);
    }
}