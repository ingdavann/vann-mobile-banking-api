package co.istad.vannbankingapi.features.auth.dto;

public record AuthResponse(
        String type,
        String accessToken,
        String refreshToken

) {
}
