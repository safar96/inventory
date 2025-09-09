package uz.inventory.app.repository.role;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.inventory.app.entity.role.RoleEntity;

import java.util.Collection;
import java.util.List;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    List<RoleEntity> findAllByName(String name);

    List<RoleEntity> findAllById(int id);
    List<RoleEntity> findAllByIdIn(Collection<Integer> id);

    RoleEntity findByName(String name);

    Page<RoleEntity> findAll(Specification<RoleEntity> specification, Pageable pageable);
}
