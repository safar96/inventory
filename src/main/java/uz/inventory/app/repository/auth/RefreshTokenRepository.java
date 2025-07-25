package uz.inventory.app.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import uz.inventory.app.entity.auth.RefreshTokenEntity;
import uz.inventory.app.entity.user.UserEntity;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByToken(String token);

    @Modifying
    int deleteByUser(UserEntity user);
}
