package uz.inventory.app.dto.util;

import lombok.Data;

@Data
public class PaginationRequestDto {
    private int page;
    private int size;
    private String sortBy;   // Sort field
    private String sortDir;  // Sort direction (asc or desc)
    private String search;     // Filter by all (optional)

}
