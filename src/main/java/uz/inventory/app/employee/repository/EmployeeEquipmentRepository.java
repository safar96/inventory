package uz.inventory.app.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uz.inventory.app.employee.entity.EmployeeEquipmentEntity;

@Repository
public interface EmployeeEquipmentRepository extends JpaRepository<EmployeeEquipmentEntity, Long>, JpaSpecificationExecutor<EmployeeEquipmentEntity> {
}
