package uz.inventory.app.section.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionEquipmentDto {
    private Long id;
    private Long sectionId;
    private String sectionName;
    private Long equipmentId;
    private String equipmentName;
    private Integer state;
}
