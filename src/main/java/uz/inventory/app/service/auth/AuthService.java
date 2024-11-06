package uz.inventory.app.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.inventory.app.config.JwtService;
import uz.inventory.app.dto.auth.RegisterUserDto;
import uz.inventory.app.dto.auth.SignInDto;
import uz.inventory.app.dto.auth.SignInResDto;
import uz.inventory.app.entity.role.RoleEntity;
import uz.inventory.app.entity.user.UserEntity;
import uz.inventory.app.enums.RoleName;
import uz.inventory.app.payload.ApiResponse;
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

    public ResponseEntity<?> signIn(SignInDto signInDto){

        try {
            Optional<UserEntity> optionalUser = userRepository.findByUsername(signInDto.getUsername());
            if (optionalUser.isPresent()){
                UserEntity user = optionalUser.get();
               boolean isPasswordValid = passwordEncoder.matches( signInDto.getPassword(),user.getPassword());
               if (isPasswordValid){
                   SignInResDto signInResDto = new SignInResDto();
                   signInResDto.setUsername(user.getUsername());
                   signInResDto.setLast_name(user.getLastName());
                   signInResDto.setFirst_name(user.getFirstName());
                   signInResDto.setMiddle_name(user.getMiddleName());
                   signInResDto.setToken(jwtService.generateToken(user));
                   signInResDto.setRole_names(user.getRoleEntities().stream().map((RoleEntity::getName)).toList());
                   return ResponseEntity.ok(signInResDto);
               }else{
                   return ResponseEntity.ok(new ApiResponse("Kiritilgan parol xato",false));
               }
            }else{
                return ResponseEntity.ok(new ApiResponse("Foydalanuvchi topilmadi",false));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Error");
        }
    }

    public ApiResponse registerUser(RegisterUserDto userDto) {
        Optional<UserEntity> optionalUser = userRepository.findByUsername(userDto.getUsername());
        if (optionalUser.isPresent()) {
            return new ApiResponse("Bunday foydalanuvchi mavjud!!!", false);
        } else {
            userRepository.save(new UserEntity(
                    userDto.getFirstName(),
                    userDto.getMiddleName(),
                    userDto.getLastName(),
                    userDto.getUsername(),
                    passwordEncoder.encode(userDto.getPassword()),
                    roleRepository.findAllByName(RoleName.admin))
            );
            return new ApiResponse("Muvaffiqiyatli saqlandi", true);
        }
    }
}
