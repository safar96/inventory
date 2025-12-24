package uz.inventory.app.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEquipmentDto {
    private Long id;
    private Long employeeId;
    private String employeeName;
    private Long equipmentId;
    private String equipmentName;
    private LocalDateTime assignedDate;
    private LocalDateTime returnedDate;
    private Integer state;
}
