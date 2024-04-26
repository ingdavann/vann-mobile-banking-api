package co.istad.vannbankingapi.features.auth;

import co.istad.vannbankingapi.features.auth.dto.AuthResponse;
import co.istad.vannbankingapi.features.auth.dto.ChangePasswordRequest;
import co.istad.vannbankingapi.features.auth.dto.LoginRequest;
import co.istad.vannbankingapi.features.auth.dto.RefreshTokenRequest;
import org.springframework.security.oauth2.jwt.Jwt;

public interface AuthService {
    void changePassword(Jwt jwt, ChangePasswordRequest changePasswordRequest);
    AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
    AuthResponse login(LoginRequest loginRequest);
}
