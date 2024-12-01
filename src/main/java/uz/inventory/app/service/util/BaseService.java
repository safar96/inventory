package uz.inventory.app.service.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import uz.inventory.app.dto.util.PaginationRequestDto;

public class BaseService {

    public <T> Page<T> getPaginatedAndFilteredData(
            PaginationRequestDto paginationRequestDto,
            Specification<T> specification,
            JpaRepository<T, ?> repository
    ) {
        Pageable pageable = PageRequest.of(
                paginationRequestDto.getPage(),
                paginationRequestDto.getSize(),
                paginationRequestDto.getSortDir().equalsIgnoreCase("desc")
                        ? Sort.by(paginationRequestDto.getSortBy()).descending()
                        : Sort.by(paginationRequestDto.getSortBy()).ascending()
        );

        if (repository instanceof JpaSpecificationExecutor) {
            return ((JpaSpecificationExecutor<T>) repository).findAll(specification, pageable);
        } else {
            return repository.findAll(pageable);
        }
    }
}
