package co.istad.vannbankingapi.features.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record AccountTransferRequest(
        @Size(max = 10000)
        BigDecimal transferLimit
) {
}
