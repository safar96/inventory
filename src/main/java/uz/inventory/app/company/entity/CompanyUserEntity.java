package uz.inventory.app.company.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.inventory.app.core.entity.template.AbsEntity;
import uz.inventory.app.user.entity.UserEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "company_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyUserEntity extends AbsEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private Integer state;
}
