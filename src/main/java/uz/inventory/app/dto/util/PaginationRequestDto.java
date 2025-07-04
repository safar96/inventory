package uz.inventory.app.dto.util;

import lombok.Data;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@Data
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class PaginationRequestDto {
    private int page;
    private int size;
    private String sortBy;   // Sort field
    private String sortDir;  // Sort direction (asc or desc)
    private String search;    // Filter by all (optional)

}
