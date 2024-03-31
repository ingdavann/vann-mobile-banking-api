package co.istad.vannbankingapi.features.cardtype.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CardTypeResponse(
        @NotBlank
        @Size(max = 100)
        String name
) {
}
