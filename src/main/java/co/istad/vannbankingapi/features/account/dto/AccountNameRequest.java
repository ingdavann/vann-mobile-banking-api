package co.istad.vannbankingapi.features.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AccountNameRequest(
        @NotBlank(message = "New name is required")
        @Size(message = "Account must be less than or equal")
        String newName
) {
}
