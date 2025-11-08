package uz.inventory.app.company.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

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
