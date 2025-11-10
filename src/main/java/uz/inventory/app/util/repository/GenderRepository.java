package uz.inventory.app.util.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.inventory.app.util.entity.GendersEntity;

@Repository
public interface GenderRepository extends JpaRepository<GendersEntity,Integer> {
}
