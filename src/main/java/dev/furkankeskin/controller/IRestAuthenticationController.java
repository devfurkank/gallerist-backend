package dev.furkankeskin.controller;

import dev.furkankeskin.dto.AuthRequest;
import dev.furkankeskin.dto.UserDTO;

public interface IRestAuthenticationController {

    public RootEntity<UserDTO> register(AuthRequest input);
}
