package uz.inventory.app.service;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.inventory.app.dto.util.PaginationDto;

@Service
public class UtilService {

    public Pageable getPageable(PaginationDto paginationDto) {
        return PageRequest.of(paginationDto.getPage(), paginationDto.getSize(),
                Sort.by(Sort.Order.asc("id")));
    }
}