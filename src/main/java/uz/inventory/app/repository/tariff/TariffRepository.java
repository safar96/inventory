package uz.inventory.app.repository.tariff;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.inventory.app.entity.tariff.TariffEntity;

import java.util.Optional;


public interface TariffRepository extends JpaRepository< TariffEntity,Long> {

    Page<TariffEntity> findAll (Pageable pageable);
    Optional<TariffEntity> findById (Long id);

}
