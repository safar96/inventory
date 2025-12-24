package uz.inventory.app.company.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.inventory.app.core.entity.template.AbsEntity;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "condition_id")
    private CompanyConditionEntity condition;

    public CompanyEntity(String name, Long parent_id, String inn, String address, String state, Long conditionId) {
        this.name = name;
        this.parent_id = parent_id;
        this.inn = inn;
        this.address = address;
        this.state = state;

    }

}
