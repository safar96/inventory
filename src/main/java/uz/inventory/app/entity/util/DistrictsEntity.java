package uz.inventory.app.entity.util;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "districts")
@Getter
@Setter
public class DistrictsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "region_id")
    private Long regionId;

}
