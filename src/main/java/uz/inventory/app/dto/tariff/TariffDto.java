package uz.inventory.app.dto.tariff;

import lombok.Data;
import uz.inventory.app.enums.TariffDurationTypeEnum;

@Data
public class TariffDto {
    private Long id;
    private String name;
    private String description;
    private String state;
    private double prise;
    private String duration_type;
    private int duration;
}
