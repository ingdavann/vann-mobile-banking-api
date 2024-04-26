package co.istad.vannbankingapi.features.auth;

import co.istad.vannbankingapi.features.auth.dto.AuthResponse;
import co.istad.vannbankingapi.features.auth.dto.ChangePasswordRequest;
import co.istad.vannbankingapi.features.auth.dto.LoginRequest;
import co.istad.vannbankingapi.features.auth.dto.RefreshTokenRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/change-password")
    void changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest,
    @AuthenticationPrincipal Jwt jwt)
    {
        authService.changePassword(jwt, changePasswordRequest);
    }

    @PostMapping("/refresh")
    AuthResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        return authService.refreshToken(refreshTokenRequest);
    }
    @PostMapping("/login")
    AuthResponse login(@Valid @RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }
}
