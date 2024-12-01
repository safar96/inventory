package uz.inventory.app.repository.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.inventory.app.entity.util.DistrictsEntity;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<DistrictsEntity,Integer> {

    List<DistrictsEntity> findAllByRegionId(Long regionId);
}
