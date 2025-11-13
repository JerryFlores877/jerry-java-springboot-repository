package com.example.interview.config;

import com.example.interview.model.Role;
import com.example.interview.model.User;
import com.example.interview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create admin user if not exists
        if (!userRepository.existsByEmail("admin@cruise.com")) {
            User admin = new User(
                    "admin@cruise.com",
                    passwordEncoder.encode("admin123"),
                    "Admin",
                    "User",
                    Role.ADMIN
            );
            userRepository.save(admin);
            System.out.println("✅ Admin user created: admin@cruise.com / admin123");
        }

        // Create regular user if not exists
        if (!userRepository.existsByEmail("user@cruise.com")) {
            User user = new User(
                    "user@cruise.com",
                    passwordEncoder.encode("user123"),
                    "Regular",
                    "User",
                    Role.USER
            );
            userRepository.save(user);
            System.out.println("✅ Regular user created: user@cruise.com / user123");
        }
    }
}
