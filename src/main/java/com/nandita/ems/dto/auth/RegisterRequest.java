package com.nandita.ems.dto.auth;

import com.nandita.ems.entity.enums.RoleName;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    @NotBlank
    private String username;

    @Email
    private String email;

    @Size(min = 8)
    private String password;

    @NotBlank
    private RoleName role;
}