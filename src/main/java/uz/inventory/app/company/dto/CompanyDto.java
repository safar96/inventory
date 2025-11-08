package uz.inventory.app.company.dto;

import lombok.Data;
import uz.inventory.app.dto.tariff.TariffDto;


@Data
public class CompanyDto {
    Long id;
    Long parent_id;
    String name;
    String inn;
    String address;
    TariffDto tarif;
    String state;
    Long condition_id;
}
