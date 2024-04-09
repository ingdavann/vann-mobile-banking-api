package co.istad.vannbankingapi.features.transaction;

import co.istad.vannbankingapi.domain.Transaction;
import co.istad.vannbankingapi.features.transaction.dto.TransactionCreateRequest;
import co.istad.vannbankingapi.features.transaction.dto.TransactionResponse;
import org.springframework.data.domain.Page;

public interface TransactionService {
    TransactionResponse transfer(TransactionCreateRequest transactionCreateRequest);

//    Page<TransactionResponse> findAllTransaction(int page, int size);
    Page<TransactionResponse> findAllTransaction(int page, int size, String sortOrder);
}
