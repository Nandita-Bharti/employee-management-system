package com.nandita.ems.service;

import com.nandita.ems.dto.auth.AuthResponse;
import com.nandita.ems.dto.auth.LoginRequest;
import com.nandita.ems.dto.auth.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}