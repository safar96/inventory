package uz.inventory.app.entity.company;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "company_tariff")
@Data
public class CompnayTariffEntity {

    @Id
    @Column(name = "company_id")
    private Long name;

    @Column(name = "region_id")
    private Long regionId;

}
