package com.nandita.ems.config;

import com.nandita.ems.entity.Role;
import com.nandita.ems.entity.enums.RoleName;
import com.nandita.ems.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {

        createRole(RoleName.ADMIN);
        createRole(RoleName.HR);
        createRole(RoleName.EMPLOYEE);
    }

    private void createRole(RoleName roleName) {

        if (!roleRepository.existsByName(roleName)) {

            Role role = Role.builder()
                    .name(roleName)
                    .description(roleName.name() + " Role")
                    .build();

            roleRepository.save(role);
        }
    }
}