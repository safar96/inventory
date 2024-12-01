package uz.inventory.app.entity.util;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "genders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GendersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public GendersEntity(String name) {
        this.name = name;
    }
}
