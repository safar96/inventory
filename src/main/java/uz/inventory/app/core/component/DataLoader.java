package uz.inventory.app.core.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.inventory.app.auth.entity.RoleEntity;
import uz.inventory.app.user.entity.UserEntity;
import uz.inventory.app.util.entity.GendersEntity;
import uz.inventory.app.auth.repository.RoleRepository;
import uz.inventory.app.user.repository.UserRepository;
import uz.inventory.app.util.repository.GenderRepository;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    private String initialMode;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final GenderRepository genderRepository;


    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {

            roleRepository.save(
                    new RoleEntity(1, "ROLE_ADMIN")
            );

            roleRepository.save(
                    new RoleEntity(2, "ROLE_USER")
            );

            roleRepository.save(
                    new RoleEntity(3, "ROLE_MODERATOR")
            );

            roleRepository.save(
                    new RoleEntity(4, "ROLE_SUPER_ADMIN")
            );

            userRepository.save(new UserEntity(
                    "admin",
                    "admin",
                    "admin",
                    "123",
                    passwordEncoder.encode("admin"),
                    roleRepository.findAllById(1)
            ));

            genderRepository.save(new GendersEntity("Erkak"));
            genderRepository.save(new GendersEntity("Ayol"));

        }
    }
}
