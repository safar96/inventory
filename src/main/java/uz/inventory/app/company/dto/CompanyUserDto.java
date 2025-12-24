package uz.inventory.app.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyUserDto {
    private Long id;
    private Long companyId;
    private String companyName;
    private Long userId;
    private String userName;
    private Integer state;
}
