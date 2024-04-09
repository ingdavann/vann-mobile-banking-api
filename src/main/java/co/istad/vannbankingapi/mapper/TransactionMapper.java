package co.istad.vannbankingapi.mapper;

import co.istad.vannbankingapi.domain.Transaction;
import co.istad.vannbankingapi.features.transaction.dto.TransactionCreateRequest;
import co.istad.vannbankingapi.features.transaction.dto.TransactionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = AccountMapper.class)
public interface TransactionMapper {
    Transaction fromTransactionCreateRequest(TransactionCreateRequest transactionCreateRequest);
    TransactionResponse toTransactionResponse(Transaction transaction);
}
