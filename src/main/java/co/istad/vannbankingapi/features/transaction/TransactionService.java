package co.istad.vannbankingapi.features.transaction;

import co.istad.vannbankingapi.features.transaction.dto.TransactionCreateRequest;
import co.istad.vannbankingapi.features.transaction.dto.TransactionResponse;

public interface TransactionService {
    TransactionResponse transfer(TransactionCreateRequest transactionCreateRequest);


}
