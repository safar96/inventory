package uz.inventory.app.entity.tariff;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.inventory.app.entity.template.AbsEntity;
import uz.inventory.app.enums.TariffDurationTypeEnum;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "tariff")
@AllArgsConstructor
@NoArgsConstructor
public class TariffEntity extends AbsEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "prise")
    private double prise;

    @Column(name = "state")
    private String state;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "duration_type")
    private TariffDurationTypeEnum durationType;

    @Column(name = "duration")
    private int duration;

}
