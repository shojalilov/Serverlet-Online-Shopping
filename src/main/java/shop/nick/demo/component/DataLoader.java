package shop.nick.demo.component;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import shop.nick.demo.entity.User;
import shop.nick.demo.entity.enums.RoleName;
import shop.nick.demo.repository.RoleRepository;
import shop.nick.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;

@Component
public class DataLoader implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Value("${spring.sql.init.mode}")
    private String initMode;

    public DataLoader(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("always")) {
            saveUser("admin@gmail.com", RoleName.ADMIN);
            saveUser("client@gmail.com", RoleName.CLIENT);
        }
    }

    public void saveUser(String username, RoleName roleName) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode("root123"));
        user.setRole(roleRepository.findByRoleName(roleName).orElseThrow(() -> new UsernameNotFoundException("getRole")));
        userRepository.save(user);
    }
}

