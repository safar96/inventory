package uz.inventory.app.auth.dto;

import lombok.Data;

@Data
public class ResRefreshToken {
    private String refresh_token;
    private String access_token;
}
