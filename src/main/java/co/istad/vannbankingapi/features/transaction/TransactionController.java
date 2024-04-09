package co.istad.vannbankingapi.features.transaction;


import co.istad.vannbankingapi.domain.Transaction;
import co.istad.vannbankingapi.features.transaction.dto.TransactionCreateRequest;
import co.istad.vannbankingapi.features.transaction.dto.TransactionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    TransactionResponse transfer(@Valid @RequestBody TransactionCreateRequest transactionCreateRequest){
        return transactionService.transfer(transactionCreateRequest);
    }

//    @GetMapping
//    Page<TransactionResponse> findAllTransaction(
//            @RequestParam(required = false, defaultValue = "0") int page,
//            @RequestParam(required = false, defaultValue = "25") int size
//    ) {
//        return transactionService.findAllTransaction(page,size);
//    }

    @GetMapping
    public Page<TransactionResponse> findAllTransaction(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size,
            String sortOrder
    ){
        return transactionService.findAllTransaction(page, size, sortOrder);
    }
}
