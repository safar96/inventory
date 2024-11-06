package uz.inventory.app.repository.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.inventory.app.entity.util.GendersEntity;

@Repository
public interface GenderRepository extends JpaRepository<GendersEntity,Integer> {
}
