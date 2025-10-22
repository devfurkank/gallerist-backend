package dev.furkankeskin.service.impl;

import dev.furkankeskin.dto.AuthRequest;
import dev.furkankeskin.dto.AuthResponse;
import dev.furkankeskin.dto.RefreshTokenRequest;
import dev.furkankeskin.dto.UserDTO;
import dev.furkankeskin.exception.BaseException;
import dev.furkankeskin.exception.ErrorMessage;
import dev.furkankeskin.exception.MessageType;
import dev.furkankeskin.jwt.JWTService;
import dev.furkankeskin.model.RefreshToken;
import dev.furkankeskin.model.User;
import dev.furkankeskin.repository.RefreshTokenRepository;
import dev.furkankeskin.repository.UserRepository;
import dev.furkankeskin.service.IAuthenticationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JWTService jwtService;

    private User createUser(AuthRequest input) {
        User user = new User();
        user.setCreateTime(new Date());
        user.setUsername(input.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(input.getPassword()));
        return user;
    }

    private RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setCreateTime(new Date());
        refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 3600 * 1000 * 4));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        return refreshToken;
    }

    @Override
    public UserDTO register(AuthRequest input) {
        UserDTO userDTO = new UserDTO();

        User savedUser = userRepository.save(createUser(input));

        BeanUtils.copyProperties(savedUser, userDTO);
        return userDTO;
    }

    @Override
    public AuthResponse authenticate(AuthRequest input) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword());
            authenticationProvider.authenticate(authenticationToken);

            Optional<User> optionalUser = userRepository.findByUsername(input.getUsername());
            String accessToken = jwtService.generateToken(optionalUser.get());
            RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(optionalUser.get()));

            return new  AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
        }catch (Exception e) {
            throw new BaseException(new ErrorMessage(e.getMessage(), MessageType.USERNAME_OR_PASSWORD_INVALID));
        }
    }

    public boolean isValidRefreshToken(Date expiredDate) {
        return new Date().before(expiredDate);
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest input) {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByRefreshToken(input.getRefreshToken());
        if (optionalRefreshToken.isEmpty()) {
            throw new BaseException(new ErrorMessage(input.getRefreshToken(), MessageType.REFRESH_TOKEN_NOT_FOUND));
        }

        if (!isValidRefreshToken(optionalRefreshToken.get().getExpiredDate())){
            throw new BaseException(new  ErrorMessage(input.getRefreshToken(), MessageType.REFRESH_TOKEN_IS_EXPIRED));
        }

        User user = optionalRefreshToken.get().getUser();
        String accessToken = jwtService.generateToken(user);
        RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(user));

        return new  AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
    }
}
