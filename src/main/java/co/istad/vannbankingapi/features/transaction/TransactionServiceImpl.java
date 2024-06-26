package co.istad.vannbankingapi.features.transaction;

import co.istad.vannbankingapi.domain.Account;
import co.istad.vannbankingapi.domain.Transaction;
import co.istad.vannbankingapi.features.account.AccountRepository;
import co.istad.vannbankingapi.features.transaction.dto.TransactionCreateRequest;
import co.istad.vannbankingapi.features.transaction.dto.TransactionResponse;
import co.istad.vannbankingapi.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountRepository accountRepository;


    @Override
    public TransactionResponse transfer(TransactionCreateRequest transactionCreateRequest) {

        // validate owner account no
        Account owner = accountRepository.findByActNo(transactionCreateRequest.ownerActNo())
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account owner has not been found"
                ));

        // validate transferReceiver account no
        Account transferReceiver = accountRepository.findByActNo(transactionCreateRequest.transferReceiverActNo())
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account Transfer receiver has not been found"
                ));

        // check amount transfer with transfer limit
        if (transactionCreateRequest.amount().doubleValue() >= owner.getTransferLimit().doubleValue()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transaction has been over the transfer limit");
        }

        // withdrawal from owner account
        owner.setBalance(owner.getBalance().subtract(transactionCreateRequest.amount()));

        // deposit to receiver
        transferReceiver.setBalance(transferReceiver.getBalance().add(transactionCreateRequest.amount()));

        Transaction transaction = transactionMapper.fromTransactionCreateRequest(transactionCreateRequest);
        transaction.setOwner(owner);
        transaction.setTransferReceiver(transferReceiver);
        transaction.setTransactionType("TRANSFER");
        transaction.setTransactionAt(LocalDateTime.now());
        transaction.setStatus(true);
        transaction = transactionRepository.save(transaction);

        return transactionMapper.toTransactionResponse(transaction);
    }

    @Override
    public Page<TransactionResponse> findAllTransaction(int page, int size, String sortOrder) {
         //validate page and size >= 0
        if (page < 0 ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Page must be greater than or equal to zero");
        }
        if (size < 1){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Size must be greater than or equal to one");
        }
        Sort sortByDate = Sort.by(sortOrder.equalsIgnoreCase("desc") ?
                Sort.Direction.DESC : Sort.Direction.ASC, "transactionAt");
        PageRequest pageRequest = PageRequest.of(page, size, sortByDate);
        Page<Transaction> transactions = transactionRepository.findAll(pageRequest);
        return transactions.map(transactionMapper::toTransactionResponse);
    }


//    @Override
//    public Page<TransactionResponse> findAllTransaction(int page, int size) {
//        // validate page and size >= 0
//        if (page < 0 ){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Page must be greater than or equal to zero");
//        }
//        if (size < 1){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Size must be greater than or equal to one");
//        }
//        Sort sortByDate = Sort.by(Sort.Direction.ASC, "transactionAt");
//        PageRequest pageRequest = PageRequest.of(page, size, sortByDate);
//        Page<Transaction> transactions = transactionRepository.findAll(pageRequest);
//        return transactions.map(transactionMapper::toTransactionResponse);
//    }
}
