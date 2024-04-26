package co.istad.vannbankingapi.features.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequest(
        @NotBlank(message = "Password is require!")
        String oldPassword,
        @NotBlank(message = "Password is require!")
        String newPassword,
        @NotBlank(message = "Password is require!")
        String confirmedPassword
) {
}
