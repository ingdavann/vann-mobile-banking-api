package co.istad.vannbankingapi.features.auth;

import co.istad.vannbankingapi.features.auth.dto.AuthResponse;
import co.istad.vannbankingapi.features.auth.dto.LoginRequest;
import co.istad.vannbankingapi.features.auth.dto.RefreshTokenRequest;
import co.istad.vannbankingapi.features.token.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final TokenService tokenService;

    // refresh token
    @Override
    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        Authentication auth = new BearerTokenAuthenticationToken(
                refreshTokenRequest.refreshToken()
        );
        auth = jwtAuthenticationProvider.authenticate(auth);
        return tokenService.createToken(auth);
    }

    // login
    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                loginRequest.phoneNumber(),
                loginRequest.password()
        );
        auth = daoAuthenticationProvider.authenticate(auth);
        return tokenService.createToken(auth);
    }
}
