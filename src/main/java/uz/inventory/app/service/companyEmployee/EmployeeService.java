package uz.inventory.app.service.companyEmployee;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.inventory.app.component.SearchSpecification;
import uz.inventory.app.dto.company.CompanyDto;
import uz.inventory.app.dto.util.PaginationRequestDto;
import uz.inventory.app.dto.companyEmployee.EmployeeDto;
import uz.inventory.app.entity.company.CompanyEntity;
import uz.inventory.app.entity.companyEmployee.EmployeeEntity;
import uz.inventory.app.repository.companyEmployee.EmployeeRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {


  final  private EmployeeRepository employeeRepository;

    public Page<EmployeeDto> getEmployee(PaginationRequestDto paginationRequestDto) {
        String searchTerm = paginationRequestDto.getSearch(); // Get search term from request
        String[] searchFields = {"firstName", "middleName", "lastName","birthDate"};
        Specification<EmployeeEntity> specification = SearchSpecification.containsSearchTerm(searchTerm, searchFields);
        Pageable pageable = PageRequest.of(
                paginationRequestDto.getPage(),
                paginationRequestDto.getSize(),
                Sort.by(Sort.Order.asc("id"))
        );

        Page<EmployeeEntity> employeePage = employeeRepository.findAll(specification, pageable);

        return employeePage.map(this::getEmployeeDto);
    }

    private EmployeeDto getEmployeeDto(EmployeeEntity employeeEntity) {
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(employeeEntity, employeeDto);
        if (employeeEntity.getCompany() != null) {
            CompanyDto companyDto = new CompanyDto();
            companyDto.setId(employeeEntity.getCompany().getId());
            companyDto.setName(employeeEntity.getCompany().getName());
            companyDto.setAddress(employeeEntity.getCompany().getAddress());
            employeeDto.setCompany(companyDto); // Include company DTO in the response
        }
        return employeeDto;
    }
    public Optional<EmployeeEntity> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public EmployeeEntity saveEmployee(EmployeeEntity employeeEntity) {
        System.out.println(employeeEntity);
        return employeeRepository.save(employeeEntity);
    }
    public EmployeeEntity updateEmployee(Long id, EmployeeEntity updatedEmployee) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setCompany(updatedEmployee.getCompany());
            employee.setFirstName(updatedEmployee.getFirstName());
            employee.setLastName(updatedEmployee.getLastName());
            employee.setMiddleName(updatedEmployee.getMiddleName());
            employee.setState(updatedEmployee.getState());
            employee.setBirthDate(updatedEmployee.getBirthDate());
            employee.setSectionId(updatedEmployee.getSectionId());
            employee.setGenderCode(updatedEmployee.getGenderCode());

            return employeeRepository.save(employee);
        }).orElseThrow(() -> new RuntimeException("Company not found with id " + id));
    }
}