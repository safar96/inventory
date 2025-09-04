package uz.inventory.app.dto.auth;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterUserDto {
    @NotBlank
    @NotNull
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank
    @NotNull
    @JsonProperty("middle_name")
    private String middleName;

    @NotBlank
    @NotNull
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank
    @NotNull
    private String username;

    @NotBlank
    @NotNull
    private String password;
}
