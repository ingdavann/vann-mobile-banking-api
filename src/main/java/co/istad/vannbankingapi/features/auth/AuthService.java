package co.istad.vannbankingapi.features.auth;

import co.istad.vannbankingapi.features.auth.dto.AuthResponse;
import co.istad.vannbankingapi.features.auth.dto.LoginRequest;
import co.istad.vannbankingapi.features.auth.dto.RefreshTokenRequest;

public interface AuthService {
    AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
    AuthResponse login(LoginRequest loginRequest);
}
