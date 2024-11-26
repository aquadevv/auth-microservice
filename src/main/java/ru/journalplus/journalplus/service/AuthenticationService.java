package ru.journalplus.journalplus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.journalplus.journalplus.dto.JwtAuthenticationResponse;
import ru.journalplus.journalplus.dto.RefreshTokenRequest;
import ru.journalplus.journalplus.dto.SignInRequest;
import ru.journalplus.journalplus.dto.SignUpRequest;
import ru.journalplus.journalplus.enums.Role;
import ru.journalplus.journalplus.model.User;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userService.create(user);

        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateAndSaveRefreshToken(user);

        return new JwtAuthenticationResponse(accessToken, refreshToken);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateAndSaveRefreshToken(user);

        return new JwtAuthenticationResponse(accessToken, refreshToken);
    }

    public JwtAuthenticationResponse updateRefreshToken(RefreshTokenRequest request) {
        var refreshToken = refreshTokenService.validateRefreshToken(request.getRefreshToken());

        var user = userService
                .userDetailsService()
                .loadUserByUsername(refreshToken.getUser().getUsername());

        var newAccessToken = jwtService.generateAccessToken(user);
        var newRefreshToken = jwtService.generateRefreshToken(user);

        refreshTokenService.updateRefreshToken(refreshToken, newRefreshToken);

        return new JwtAuthenticationResponse(newAccessToken, newRefreshToken);
    }
}
