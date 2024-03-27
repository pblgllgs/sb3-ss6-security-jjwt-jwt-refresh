package com.pblgllgs.security;
/*
 *
 * @author pblgl
 * Created on 27-03-2024
 *
 */

import com.pblgllgs.security.entities.User;
import com.pblgllgs.security.enums.Role;
import com.pblgllgs.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if ((long) userRepository.findAll().size() == 0){
            User user = User.builder()
                    .role(Role.USER)
                    .firstName("user")
                    .lastName("user")
                    .email("user@gmail.com")
                    .password(passwordEncoder.encode("pass"))
                    .build();

            userRepository.save(user);

            User admin = User.builder()
                    .role(Role.ADMIN)
                    .firstName("admin")
                    .lastName("admin")
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("pass"))
                    .build();

            userRepository.save(admin);
        }
    }
}
