package co.istad.vannbankingapi.features.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(
        @NotBlank(message = "Refresh token is require!")
        String refreshToken
) {
}
