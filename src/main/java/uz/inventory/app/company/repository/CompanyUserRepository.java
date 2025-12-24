package uz.inventory.app.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uz.inventory.app.company.entity.CompanyUserEntity;

@Repository
public interface CompanyUserRepository extends JpaRepository<CompanyUserEntity, Long>, JpaSpecificationExecutor<CompanyUserEntity> {
}
