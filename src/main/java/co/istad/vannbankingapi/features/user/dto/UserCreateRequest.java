package co.istad.vannbankingapi.features.user.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserCreateRequest(
        @NotNull
        @Max(9999)
        @Positive
        Integer pin,

        @NotNull
        @Size(max = 20)
        String phoneNumber,

        @NotBlank
        String password,
        @NotNull
        String confirmPassword,

        @NotBlank
        @Size(max = 20)
        String nationalId,

        @NotBlank
        @Size(max = 40)
        String name,

        @NotBlank
        @Size(max = 6)
        String gender,

        @NotBlank
        @Size(max = 20)
        LocalDate dob,

        @Size(max = 20)
        String studentIdCard
) {
}
