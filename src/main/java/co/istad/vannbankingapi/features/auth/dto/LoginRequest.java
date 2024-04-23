package co.istad.vannbankingapi.features.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Phone number is require")
        String phoneNumber,

        @NotBlank(message = "Password is require")
        String password
) {
}
