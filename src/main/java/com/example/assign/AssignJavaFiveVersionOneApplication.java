package com.example.assign;

import com.example.assign.role.Role;
import com.example.assign.role.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AssignJavaFiveVersionOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssignJavaFiveVersionOneApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(RoleRepo roleRepo) {
//        return args -> {
//            Role a = new Role("ADMIN", "admin:read");
//            Role aa = new Role("ADMIN", "admin:create");
//            Role aaa = new Role("ADMIN", "admin:update");
//            Role aaaa = new Role("ADMIN", "admin:delete");
//            Role u = new Role("USER", "user:read");
//            Role m = new Role("MANAGER", "manager:read");
//            roleRepo.save(a);
//            roleRepo.save(aa);
//            roleRepo.save(aaa);
//            roleRepo.save(aaaa);
//            roleRepo.save(u);
//            roleRepo.save(m);
//        };
//    }
}
