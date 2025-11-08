package uz.inventory.app.employee.dto;

import lombok.Data;

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
