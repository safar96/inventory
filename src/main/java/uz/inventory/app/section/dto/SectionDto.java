package uz.inventory.app.section.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionDto {
    private Long id;
    private String name;
    private String description;
    private Long companyId;
    private String companyName;
    private Integer state;
}
