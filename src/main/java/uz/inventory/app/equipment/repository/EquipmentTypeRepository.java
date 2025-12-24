package uz.inventory.app.equipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uz.inventory.app.equipment.entity.EquipmentTypeEntity;

@Repository
public interface EquipmentTypeRepository extends JpaRepository<EquipmentTypeEntity, Long>, JpaSpecificationExecutor<EquipmentTypeEntity> {
}
