package uz.inventory.app.auth.entity;




import jakarta.persistence.*;
import lombok.Data;
import uz.inventory.app.entity.user.UserEntity;

import java.time.Instant;


@Data
@Entity(name = "refresh_token")
public class RefreshTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;

}
