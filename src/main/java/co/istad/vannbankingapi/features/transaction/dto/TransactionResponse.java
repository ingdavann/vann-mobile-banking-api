package co.istad.vannbankingapi.features.transaction.dto;

import co.istad.vannbankingapi.features.account.dto.AccountSnippetResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(
        AccountSnippetResponse owner,

        AccountSnippetResponse transferReceiver,
        BigDecimal amount,

        String remark,

        String transactionType,

        Boolean status,

        LocalDateTime transactionAt

) {

}
