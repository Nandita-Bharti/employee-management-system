package com.nandita.ems.service.impl;

import com.nandita.ems.dto.auth.AuthResponse;
import com.nandita.ems.dto.auth.LoginRequest;
import com.nandita.ems.dto.auth.RegisterRequest;
import com.nandita.ems.repository.RoleRepository;
import com.nandita.ems.repository.UserRepository;
import com.nandita.ems.security.jwt.JwtService;
import com.nandita.ems.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
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

        return null;
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        return null;
    }
}