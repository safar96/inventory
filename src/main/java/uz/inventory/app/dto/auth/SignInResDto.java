package uz.inventory.app.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInResDto {
    private String username;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String access_token;
    private String refresh_token;
    private List<String> role_names;
}
