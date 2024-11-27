package uz.inventory.app.dto.company;

import lombok.Data;

@Data
public class PaginationRequestDto {
    private int page;
    private int size;
    private String sortBy;   // Sort field
    private String sortDir;  // Sort direction (asc or desc)
    private String name;     // Filter by name (optional)
    private String inn;      // Filter by INN (optional)
}
