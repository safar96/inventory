package uz.inventory.app.employee.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.inventory.app.core.entity.template.AbsEntity;
import uz.inventory.app.equipment.entity.EquipmentEntity;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employee_equipment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEquipmentEntity extends AbsEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id", nullable = false)
    private EquipmentEntity equipment;

    @Column(name = "assigned_date")
    private LocalDateTime assignedDate;

    @Column(name = "returned_date")
    private LocalDateTime returnedDate;

    private Integer state;
}
