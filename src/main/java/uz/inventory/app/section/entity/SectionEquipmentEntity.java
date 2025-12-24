package uz.inventory.app.section.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.inventory.app.core.entity.template.AbsEntity;
import uz.inventory.app.equipment.entity.EquipmentEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "section_equipment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionEquipmentEntity extends AbsEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id", nullable = false)
    private SectionEntity section;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id", nullable = false)
    private EquipmentEntity equipment;

    private Integer state;
}
