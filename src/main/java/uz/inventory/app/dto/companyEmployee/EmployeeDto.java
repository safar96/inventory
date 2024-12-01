package uz.inventory.app.dto.companyEmployee;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private String genderCode;
    private Date birthDate;
    private Long sectionId;
    private String state;
    private Long companyId;
}
