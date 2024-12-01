package uz.inventory.app.service.companyEmployee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.inventory.app.dto.util.PaginationRequestDto;
import uz.inventory.app.dto.companyEmployee.EmployeeDto;
import uz.inventory.app.dto.companyEmployee.EmployeeSpecification;
import uz.inventory.app.entity.company.CompanyEntity;
import uz.inventory.app.entity.companyEmployee.EmployeeEntity;
import uz.inventory.app.repository.companyEmployee.EmployeeRepository;
import uz.inventory.app.repository.company.CompanyRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public Page<EmployeeEntity> getEmployee(PaginationRequestDto paginationRequestDto) {
        Pageable pageable = PageRequest.of(paginationRequestDto.getPage(), paginationRequestDto.getSize(),
                paginationRequestDto.getSortDir().equals("desc") ? Sort.by(Sort.Order.desc(paginationRequestDto.getSortBy())) :
                        Sort.by(Sort.Order.asc(paginationRequestDto.getSortBy())));
        Specification<EmployeeEntity> specification = Specification.where(null);

        return employeeRepository.findAll(specification, pageable);
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