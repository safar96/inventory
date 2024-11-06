package uz.inventory.app.dto.user;


import lombok.Data;

@Data
public class TokenRefreshResponseDto {

    private String access_token;
    private String refresh_token;

    public TokenRefreshResponseDto(String access_token, String refresh_token) {
        this.access_token = access_token;
        this.refresh_token = refresh_token;
    }
}
