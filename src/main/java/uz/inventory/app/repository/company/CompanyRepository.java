package uz.inventory.app.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.inventory.app.entity.company.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    Page<CompanyEntity> findAll(Pageable pageable);

    Page<CompanyEntity> findAll(Specification<CompanyEntity> specification, Pageable pageable);
}
