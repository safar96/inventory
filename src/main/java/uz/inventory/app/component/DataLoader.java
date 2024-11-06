package uz.inventory.app.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.inventory.app.entity.role.RoleEntity;
import uz.inventory.app.entity.user.UserEntity;
import uz.inventory.app.enums.RoleName;
import uz.inventory.app.repository.role.RoleRepository;
import uz.inventory.app.repository.user.UserRepository;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    private String initialMode;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {

            roleRepository.save(
                    new RoleEntity(1, RoleName.superAdmin)
            );

            roleRepository.save(
                    new RoleEntity(2, RoleName.admin)
            );

            roleRepository.save(
                    new RoleEntity(3, RoleName.moderator)
            );

            roleRepository.save(
                    new RoleEntity(4, RoleName.user)
            );

            userRepository.save(new UserEntity(
                    "admin",
                    "admin",
                    "admin",
                    "123",
                    passwordEncoder.encode("admin"),
                    roleRepository.findAllById(1)
            ));

        }
    }
}
