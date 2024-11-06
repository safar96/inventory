package uz.inventory.app.payload;

import lombok.Data;

@Data
public class JwtResponse {
    private String access_token;
    private String refresh_token;
    private String token_type = "Bearer";

    public JwtResponse(String access_token, String refresh_token) {
        this.access_token = access_token;
        this.refresh_token = refresh_token;
    }
}
