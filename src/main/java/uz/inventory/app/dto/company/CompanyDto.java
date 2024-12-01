package uz.inventory.app.dto.company;

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
