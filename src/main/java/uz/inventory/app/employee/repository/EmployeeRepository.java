package uz.inventory.app.employee.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.inventory.app.employee.entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    Page<EmployeeEntity> findAll(Specification<EmployeeEntity> specification, Pageable pageable);

    Page<EmployeeEntity> findAllByCompany_Id(Long company_id, Pageable pageable);
}
