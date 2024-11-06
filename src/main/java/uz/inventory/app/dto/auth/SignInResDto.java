package uz.inventory.app.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.inventory.app.enums.RoleName;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInResDto {
    private String username;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String token;
    private List<RoleName> role_names;
}
