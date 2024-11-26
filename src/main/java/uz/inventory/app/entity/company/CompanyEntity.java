package uz.inventory.app.entity.company;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "companies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent_id")
    private Long parent_id;

    @Column(name = "name")
    private String name;

    @Column(name = "inn")
    private String inn;

    @Column(name = "address")
    private String address;

    @Column(name = "state")
    private String state;

    @Column(name = "condition_id")
    private Long conditionId;

    @Column(name = "cr_by")

    private Long crBy;

    @Column(name = "cr_on", nullable = false)
    private LocalDate crOn;

    @Column(name = "up_by")
    private Long upBy;

    @Column(name = "up_on")
    private LocalDate upOn;

}
