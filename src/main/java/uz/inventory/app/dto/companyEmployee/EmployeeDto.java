package uz.inventory.app.dto.companyEmployee;

import lombok.Data;
import uz.inventory.app.dto.company.CompanyDto;

import java.util.Date;

@Data
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String genderCode;
    private Date birthDate;
    private Long sectionId;
    private String state;
    private Long companyId;
}
