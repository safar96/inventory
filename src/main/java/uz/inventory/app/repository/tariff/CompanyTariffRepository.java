package uz.inventory.app.repository.tariff;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.inventory.app.entity.tariff.CompanyTariffEntity;

import java.util.Optional;

public interface CompanyTariffRepository extends JpaRepository<CompanyTariffEntity,Long> {
    Optional<CompanyTariffEntity> findByCompanyIdAndTariffIdAndState(long companyId, long tariffId, String state);
}
