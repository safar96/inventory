package uz.inventory.app.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInDto {

    @NotBlank(message = "username is required")
    @NotNull
    private String username;

    @NotBlank(message = "password is required")
    @NotNull
    private String password;
}
