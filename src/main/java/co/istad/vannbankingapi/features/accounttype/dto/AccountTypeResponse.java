package co.istad.vannbankingapi.features.accounttype.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AccountTypeResponse(
        @NotBlank
        @Size(max = 50)
        String name,
        String description
) {
}
