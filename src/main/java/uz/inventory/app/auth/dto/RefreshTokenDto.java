package uz.inventory.app.auth.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RefreshTokenDto {
    @NotNull
    private String token;
}
