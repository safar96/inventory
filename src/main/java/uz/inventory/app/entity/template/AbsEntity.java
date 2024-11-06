package uz.inventory.app.entity.template;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.inventory.app.entity.user.UserEntity;

import java.sql.Timestamp;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp updatedAt;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false)
    private UserEntity createdBy;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity updatedBy;
}
