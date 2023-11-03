package com.example.assign.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:application.yml")
public class PermissionConfig {

    private final Environment environment;

    @Bean
    public List<String> adminPermissions(){
        return List.of("admin:read", "admin:create", "admin:update", "admin:delete");
    }

    @Bean
    public String adminRead() {
        return environment.getProperty("admin.admin-read");
    }

    @Bean
    public String adminCreate() {
        return environment.getProperty("admin.admin-read");
    }

    @Bean
    public String adminUpdate() {
        return environment.getProperty("admin.admin-read");
    }

    @Bean
    public String adminDelete() {
        return environment.getProperty("admin.admin-read");
    }

    @Bean
    public String userRead() {
        return environment.getProperty("admin.admin-read");
    }

    @Bean
    public String managerRead() {
        return environment.getProperty("admin.admin-read");
    }
}
