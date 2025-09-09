package com.urnovie.tupeli.config;

import com.urnovie.tupeli.entity.User;
import com.urnovie.tupeli.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("admin@correo.com").isEmpty()) {
                User user = new User();
                user.setUsername("Admin");
                user.setEmail("admin@correo.com");
                user.setPassword(passwordEncoder.encode("1234"));
                userRepository.save(user);
                System.out.println("✅ Usuario inicial creado: admin@correo.com / 1234");
            }
        };
    }
}
