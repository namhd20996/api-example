package com.example.assign;

import com.example.assign.role.Role;
import com.example.assign.role.RoleRepo;
import com.example.assign.user.User;
import com.example.assign.user.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class AssignJavaFiveVersionOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssignJavaFiveVersionOneApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(RoleRepo roleRepo, PasswordEncoder encoder, UserRepo userRepo) {
        return args -> {
            List<Role> rolesAdd = roleRepo.findRolesByName("ADMIN")
                    .orElse(null);

            if (rolesAdd.isEmpty()) {
                List<Role> roles = List.of(new Role("ADMIN", "admin:read"),
                        new Role("ADMIN", "admin:create"),
                        new Role("ADMIN", "admin:update"),
                        new Role("ADMIN", "admin:delete"),
                        new Role("USER", "user:read"),
                        new Role("MANAGER", "manager:read")
                );
                roleRepo.saveAllAndFlush(roles);

                userRepo.saveAndFlush(
                        User.builder()
                                .username("sellsad")
                                .email("hoangduynam20996@gmail.com")
                                .password(encoder.encode("123@bBbb"))
                                .status(1)
                                .roles(rolesAdd)
                                .build()
                );
            }
        };
    }
}
