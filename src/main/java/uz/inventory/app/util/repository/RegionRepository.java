package uz.inventory.app.util.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.inventory.app.util.entity.RegionsEntity;
import java.util.Optional;


@Repository
public interface RegionRepository extends JpaRepository<RegionsEntity,Integer> {

    Optional<RegionsEntity> findByName(String name);

}
