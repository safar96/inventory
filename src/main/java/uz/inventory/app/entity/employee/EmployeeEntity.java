package uz.inventory.app.entity.employee;

import jakarta.persistence.*;
import lombok.*;
import uz.inventory.app.entity.company.CompanyEntity;
import uz.inventory.app.entity.template.AbsEntity;

import java.util.Date;

@Entity
@Data
@Table(name = "Employee")
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity extends AbsEntity {

    @ManyToOne(fetch = FetchType.LAZY) // Defines the relationship
    @JoinColumn(name = "company_id", nullable = false) // Specifies the foreign key column
    private CompanyEntity company;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender_code")
    private String genderCode;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "section_id")
    private Long sectionId;

    @Column(name="state")
    private String state;
}
