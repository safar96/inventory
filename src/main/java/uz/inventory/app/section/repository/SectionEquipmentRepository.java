package uz.inventory.app.section.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uz.inventory.app.section.entity.SectionEquipmentEntity;

@Repository
public interface SectionEquipmentRepository extends JpaRepository<SectionEquipmentEntity, Long>, JpaSpecificationExecutor<SectionEquipmentEntity> {
}
