package uz.inventory.app.entity.company;

import jakarta.persistence.*;
import lombok.*;
import uz.inventory.app.entity.template.AbsEntity;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "company")
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEntity extends AbsEntity {

    @Column(name = "parent_id")
    private Long parent_id;

    @Column(name = "name")
    private String name;

    @Column(name = "inn")
    private String inn;

    @Column(name = "address")
    private String address;

    @Column(name = "state")
    private String state;

    @Column(name = "condition_id")
    private Long conditionId;

}
