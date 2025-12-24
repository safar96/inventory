package uz.inventory.app.equipment.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.inventory.app.core.entity.template.AbsEntity;
import uz.inventory.app.company.entity.CompanyEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "equipment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentEntity extends AbsEntity {

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "inventory_number")
    private String inventoryNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_type_id")
    private EquipmentTypeEntity equipmentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    private Integer state;

    @Column(name = "image_id")
    private Long imageId;
}
