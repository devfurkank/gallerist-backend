package dev.furkankeskin.service.impl;

import dev.furkankeskin.dto.AuthRequest;
import dev.furkankeskin.dto.UserDTO;
import dev.furkankeskin.model.User;
import dev.furkankeskin.repository.UserRepository;
import dev.furkankeskin.service.IAuthenticationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private User createUser(AuthRequest input) {
        User user = new User();
        user.setCreateTime(new Date());
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        return user;
    }

    @Override
    public UserDTO register(AuthRequest input) {
        UserDTO userDTO = new UserDTO();

        User savedUser = userRepository.save(createUser(input));

        BeanUtils.copyProperties(savedUser, userDTO);
        return userDTO;
    }
}
