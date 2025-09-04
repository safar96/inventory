package uz.inventory.app.controller.auth;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import uz.inventory.app.dto.auth.*;
import uz.inventory.app.payload.CustomApiResponse;
import uz.inventory.app.service.auth.AuthService;

@CrossOrigin
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "APIs for managing users")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/signIn")
    @Operation(summary = "Login to account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SignInResDto.class))}),
            @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomApiResponse.class))}),
            @ApiResponse(responseCode = "404", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomApiResponse.class))}),
            @ApiResponse(responseCode = "409", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomApiResponse.class))}),
    })
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInDto signInDto) {
        return authService.signIn(signInDto);
    }


    @PostMapping("/refresh/token")
    @Operation(summary = "Change access token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResRefreshToken.class))}),
            @ApiResponse(responseCode = "401", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomApiResponse.class))}),
    })
    public ResponseEntity<?> refresh(@Valid@RequestBody RefreshTokenDto refreshTokenDto) {
        return authService.refresh(refreshTokenDto);
    }


    @PostMapping("/user/register")
    @Operation(summary = "Register new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResRefreshToken.class))}),
            @ApiResponse(responseCode = "409", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomApiResponse.class))}),
    })
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserDto registerUserDto) {
        return authService.registerUser(registerUserDto);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new CustomApiResponse("Log out successfully", true));
    }


}