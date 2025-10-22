package dev.furkankeskin.controller;

import dev.furkankeskin.dto.AuthRequest;
import dev.furkankeskin.dto.AuthResponse;
import dev.furkankeskin.dto.RefreshTokenRequest;
import dev.furkankeskin.dto.UserDTO;

public interface IRestAuthenticationController {

    public RootEntity<UserDTO> register(AuthRequest input);

    public RootEntity<AuthResponse> authenticate(AuthRequest input);

    public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);
}
