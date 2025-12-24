package uz.inventory.app.section.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.inventory.app.core.entity.template.AbsEntity;
import uz.inventory.app.company.entity.CompanyEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "section")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionEntity extends AbsEntity {

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    private Integer state;
}
