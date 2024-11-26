package ru.journalplus.journalplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.journalplus.journalplus.dto.JwtAuthenticationResponse;
import ru.journalplus.journalplus.dto.RefreshTokenRequest;
import ru.journalplus.journalplus.dto.SignInRequest;
import ru.journalplus.journalplus.dto.SignUpRequest;
import ru.journalplus.journalplus.service.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }

    @Operation(summary = "Обновление токенов")
    @PostMapping("/refresh")
    public JwtAuthenticationResponse refreshToken(@RequestBody @Valid RefreshTokenRequest request) {
        return authenticationService.updateRefreshToken(request);
    }
}
