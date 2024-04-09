package co.istad.vannbankingapi.features.transaction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransactionCreateRequest(
        @NotBlank(message = "Transfer account no of owner is requires")
        String ownerActNo,

        @NotBlank(message = "Transfer account no of owner is required")
        String transferReceiverActNo,

        @NotNull(message = "Amount must be greater than zero")
        @Positive
        BigDecimal amount,

        String remark

) {

}
