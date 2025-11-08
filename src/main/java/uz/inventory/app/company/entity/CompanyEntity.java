package uz.inventory.app.company.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.inventory.app.entity.template.AbsEntity;

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

    public CompanyEntity(String name, Long parent_id, String inn, String address, String state, Long conditionId) {
        this.name = name;
        this.parent_id = parent_id;
        this.inn = inn;
        this.address = address;
        this.state = state;

    }

}
