package co.istad.vannbankingapi.features.user.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

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
        @NotBlank
        String confirmPassword,

        @NotBlank
        @Size(max = 20)
        String nationalCardId,

        @NotBlank
        @Size(max = 40)
        String name,

        @NotBlank
        @Size(max = 6)
        String gender,

        @NotNull
        LocalDate dob,

        @Size(max = 20)
        String studentIdCard,

        @NotEmpty
        List<RoleRequest> roles

) {
}
