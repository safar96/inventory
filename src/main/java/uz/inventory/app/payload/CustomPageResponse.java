package uz.inventory.app.payload;

import lombok.Data;
import org.springframework.data.domain.Page;
import java.util.List;

@Data
public class CustomPageResponse {

    private List<?> data;
    private int current_page;
    private long total_items;
    private int total_pages;

    public static <T> CustomPageResponse setPageData(Page<T> pageData, List<?> data) {
        CustomPageResponse response = new CustomPageResponse();
        response.setCurrent_page(pageData.getPageable().getPageNumber());
        response.setTotal_pages(pageData.getTotalPages());
        response.setTotal_items(pageData.getTotalElements());
        response.setData(data);
        return response;
    }
}
