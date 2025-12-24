package uz.inventory.app.section.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uz.inventory.app.section.entity.SectionEntity;

@Repository
public interface SectionRepository extends JpaRepository<SectionEntity, Long>, JpaSpecificationExecutor<SectionEntity> {
}
