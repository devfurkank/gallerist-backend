package dev.furkankeskin.controller.impl;

import dev.furkankeskin.controller.IRestAuthenticationController;
import dev.furkankeskin.controller.RestBaseController;
import dev.furkankeskin.controller.RootEntity;
import dev.furkankeskin.dto.AuthRequest;
import dev.furkankeskin.dto.UserDTO;
import dev.furkankeskin.service.IAuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping("/register")
    @Override
    public RootEntity<UserDTO> register(@Valid @RequestBody AuthRequest input) {
        return ok(authenticationService.register(input));
    }
}
