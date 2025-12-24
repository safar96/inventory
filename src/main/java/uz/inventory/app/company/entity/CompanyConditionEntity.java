package uz.inventory.app.company.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.inventory.app.core.entity.template.AbsEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "company_condition")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyConditionEntity extends AbsEntity {

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer state;
}
