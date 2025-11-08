package uz.inventory.app.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.inventory.app.config.JwtService;
import uz.inventory.app.auth.dto.*;
import uz.inventory.app.entity.role.RoleEntity;
import uz.inventory.app.entity.user.UserEntity;
import uz.inventory.app.payload.CustomApiResponse;
import uz.inventory.app.repository.role.RoleRepository;
import uz.inventory.app.repository.user.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserDTLService userDTLService;

    public ResponseEntity<?> signIn(SignInDto signInDto) {
        try {
            Optional<UserEntity> optionalUser = userRepository.findByUsername(signInDto.getUsername());
            if (optionalUser.isPresent()) {
                UserEntity user = optionalUser.get();
                boolean isPasswordValid = passwordEncoder.matches(signInDto.getPassword(), user.getPassword());
                if (isPasswordValid) {
                    SignInResDto signInResDto = new SignInResDto();
                    signInResDto.setUsername(user.getUsername());
                    signInResDto.setLast_name(user.getLastName());
                    signInResDto.setFirst_name(user.getFirstName());
                    signInResDto.setMiddle_name(user.getMiddleName());
                    signInResDto.setAccess_token(jwtService.generateToken(user));
                    signInResDto.setRefresh_token(jwtService.generateRefreshToken(user));
                    signInResDto.setRole_names(user.getRoleEntities().stream().map((RoleEntity::getName)).toList());
                    return ResponseEntity.ok(signInResDto);
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomApiResponse("Kiritilgan parol xato", false));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomApiResponse("Foydalanuvchi topilmadi", false));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomApiResponse(e.getMessage(), false));
        }
    }

    public ResponseEntity<?> refresh(RefreshTokenDto refreshTokenDto) {
        if (jwtService.isTokenExpired(refreshTokenDto.getToken())) {
            String username = jwtService.extractUsername(refreshTokenDto.getToken());
            UserDetails user = userDTLService.loadUserByUsername(username);
            String newAccessToken = jwtService.generateToken(user);
            String newRefreshToken = jwtService.generateRefreshToken(user);

            ResRefreshToken resRefreshToken = new ResRefreshToken();
            resRefreshToken.setRefresh_token(newRefreshToken);
            resRefreshToken.setAccess_token(newAccessToken);
            return ResponseEntity.ok(resRefreshToken);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new CustomApiResponse("Refresh token is expired", false));
        }
    }

    public ResponseEntity<?> registerUser(RegisterUserDto userDto) {
        Optional<UserEntity> optionalUser = userRepository.findByUsername(userDto.getUsername());
        if (optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomApiResponse("Bunday foydalanuvchi mavjud!!!", false));
        } else {
            userRepository.save(new UserEntity(
                    userDto.getFirstName(),
                    userDto.getMiddleName(),
                    userDto.getLastName(),
                    userDto.getUsername(),
                    passwordEncoder.encode(userDto.getPassword()),
                    roleRepository.findAllById(1))
            );
            return ResponseEntity.ok(new CustomApiResponse("Muvaffiqiyatli saqlandi", true));
        }
    }
}
