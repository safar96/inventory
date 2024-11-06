package uz.inventory.app.dto.auth;


import lombok.Data;

@Data
public class RegisterUserDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String password;
}
