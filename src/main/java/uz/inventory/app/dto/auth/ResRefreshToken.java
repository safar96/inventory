package uz.inventory.app.dto.auth;

import lombok.Data;

@Data
public class ResRefreshToken {
    private String refresh_token;
    private String access_token;
}
