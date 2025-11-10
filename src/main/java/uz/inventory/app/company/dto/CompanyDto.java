package uz.inventory.app.company.dto;

import lombok.Data;


@Data
public class CompanyDto {
    Long id;
    Long parent_id;
    String name;
    String inn;
    String address;
    String state;
    Long condition_id;
}
