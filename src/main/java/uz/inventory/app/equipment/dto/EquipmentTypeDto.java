package uz.inventory.app.equipment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentTypeDto {
    private Long id;
    private String name;
    private String description;
    private Integer state;
}
