package uz.inventory.app.dto.company;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CompanyDto {
    Long id;
    Long parent_id;
    String name;
    String inn;
    String address;
    String state;
    Long condition_id;
    Long cr_by;
    Date cr_on;
    Long up_by;
    Date up_on;
}
