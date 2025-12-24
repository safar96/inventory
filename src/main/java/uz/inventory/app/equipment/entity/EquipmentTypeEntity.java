package uz.inventory.app.equipment.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.inventory.app.core.entity.template.AbsEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "equipment_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentTypeEntity extends AbsEntity {

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer state;
}
