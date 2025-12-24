package uz.inventory.app.equipment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDto {
    private Long id;
    private String name;
    private String description;
    private String serialNumber;
    private String inventoryNumber;
    private Long equipmentTypeId;
    private String equipmentTypeName;
    private Long companyId;
    private String companyName;
    private Integer state;
    private Long imageId;
}
