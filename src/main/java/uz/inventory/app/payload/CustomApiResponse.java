package uz.inventory.app.payload;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomApiResponse {

    private String message;

    @Schema(defaultValue = "false")
    private boolean success;

}
