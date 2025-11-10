package uz.inventory.app.user.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class RefreshTokenRequestDto {

    @NotBlank
    private String refresh_token;
}
