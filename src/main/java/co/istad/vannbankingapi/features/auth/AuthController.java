package co.istad.vannbankingapi.features.auth;

import co.istad.vannbankingapi.features.auth.dto.AuthResponse;
import co.istad.vannbankingapi.features.auth.dto.LoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    AuthResponse login(@Valid @RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }
}
