package com.nandita.ems.service.impl;

import com.nandita.ems.dto.auth.AuthResponse;
import com.nandita.ems.dto.auth.LoginRequest;
import com.nandita.ems.dto.auth.RegisterRequest;
import com.nandita.ems.entity.User;
import com.nandita.ems.repository.RoleRepository;
import com.nandita.ems.repository.UserRepository;
import com.nandita.ems.security.jwt.JwtService;
import com.nandita.ems.security.user.CustomUserPrincipal;
import com.nandita.ems.service.AuthService;
import com.nandita.ems.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Role role = roleRepository.findByName(request.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(true)
                .accountNonLocked(true)
                .role(role)
                .enabled(true)
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(
                new CustomUserPrincipal(user));

        return AuthResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(
                new CustomUserPrincipal(user));

        return AuthResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .build();
    }
}