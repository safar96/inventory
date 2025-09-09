package uz.inventory.app.service.role;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.inventory.app.component.SearchSpecification;
import uz.inventory.app.dto.role.RolesDto;
import uz.inventory.app.dto.util.PaginationRequestDto;
import uz.inventory.app.entity.role.RoleEntity;
import uz.inventory.app.payload.CustomPageResponse;
import uz.inventory.app.repository.role.RoleRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    final private RoleRepository roleRepository;

    public ResponseEntity<?> getRoles(PaginationRequestDto paginationRequestDto) {
        String searchTerm = paginationRequestDto.getSearch();
        String[] searchFields = {"name","id"};
        Specification<RoleEntity> specification = SearchSpecification.containsSearchTerm(searchTerm, searchFields);
        Pageable pageable = PageRequest.of(
                paginationRequestDto.getPage(),
                paginationRequestDto.getSize(),
                Sort.by(Sort.Order.asc("id"))
        );

        Page<RoleEntity> roleEntities = roleRepository.findAll(specification, pageable);
        List<RolesDto> rolesDtos = roleEntities.map(role -> {
            RolesDto rolesDto = new RolesDto();
            rolesDto.setId(role.getId());
            rolesDto.setName(role.getName());
            return rolesDto;
        }).stream().toList();
        return ResponseEntity.ok(CustomPageResponse.setPageData(roleEntities,rolesDtos)) ;
    }

}
