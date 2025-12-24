package uz.inventory.app.employee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.inventory.app.employee.dto.EmployeeDto;
import uz.inventory.app.company.entity.CompanyEntity;
import uz.inventory.app.section.entity.SectionEntity;
import uz.inventory.app.employee.entity.EmployeeEntity;
import uz.inventory.app.employee.repository.EmployeeRepository;
import uz.inventory.app.company.repository.CompanyRepository;
import uz.inventory.app.section.repository.SectionRepository;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    final private EmployeeRepository employeeRepository;
    final private CompanyRepository companyRepository;
    final private SectionRepository sectionRepository;


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

        if (employeeRequestDto.getSectionId() != null) {
            SectionEntity section = sectionRepository.findById(employeeRequestDto.getSectionId())
                    .orElseThrow(() -> new RuntimeException("Section not found with id " + employeeRequestDto.getSectionId()));
            employee.setSection(section);
        }

        employee.setState(employeeRequestDto.getState());
        employee.setCompany(company);

        return employeeRepository.save(employee);
    }
}