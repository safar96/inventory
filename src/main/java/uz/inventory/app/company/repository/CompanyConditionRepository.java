package uz.inventory.app.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uz.inventory.app.company.entity.CompanyConditionEntity;

@Repository
public interface CompanyConditionRepository extends JpaRepository<CompanyConditionEntity, Long>, JpaSpecificationExecutor<CompanyConditionEntity> {
}
