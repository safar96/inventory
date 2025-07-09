package uz.inventory.app.dto.auth;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterUserDto {
    @NotBlank
    @NotNull
    private String first_name;

    @NotBlank
    @NotNull
    private String middle_name;

    @NotBlank
    @NotNull
    private String last_name;

    @NotBlank
    @NotNull
    private String username;

    @NotBlank
    @NotNull
    private String password;
}
