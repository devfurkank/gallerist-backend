package dev.furkankeskin.service;

import dev.furkankeskin.dto.AuthRequest;
import dev.furkankeskin.dto.UserDTO;

public interface IAuthenticationService {

    public UserDTO register(AuthRequest input);
}
