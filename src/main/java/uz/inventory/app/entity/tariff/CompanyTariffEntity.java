package uz.inventory.app.entity.tariff;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.inventory.app.entity.template.AbsEntity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "company_tariff")
@AllArgsConstructor
@NoArgsConstructor
public class CompanyTariffEntity extends AbsEntity {

    @Column(name = "company_id")
    private long companyId;

    @Column(name = "tariff_id")
    private long tariffId;

    @Column(name = "state")
    private String state;

    @Column(name = "expire_date",nullable = false)
    private Date expireDate;
}
