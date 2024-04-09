package co.istad.vannbankingapi.features.account;

import co.istad.vannbankingapi.features.account.dto.AccountCreateRequest;
import co.istad.vannbankingapi.features.account.dto.AccountNameRequest;
import co.istad.vannbankingapi.features.account.dto.AccountResponse;
import co.istad.vannbankingapi.features.account.dto.AccountTransferRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor

public class AccountController {
    private final AccountService accountService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNew(@Valid @RequestBody AccountCreateRequest accountCreateRequest) {
        accountService.createNew(accountCreateRequest);
    }

    @GetMapping("/{actNo}")
    AccountResponse findByActNo(@PathVariable String actNo){
        return accountService.findByActNo(actNo);
    }

    @PutMapping("{actNo}/rename")
    AccountResponse renameByActNo(@PathVariable String actNo , @RequestBody AccountNameRequest renameAccount){
        return accountService.renameByActNo(actNo,renameAccount);
    }

    @PutMapping("{actNo}/hide")
    void hiddenAccountByActNo(@PathVariable String actNo){
        accountService.hiddenAccount(actNo);
    }

    @GetMapping
    Page<AccountResponse> findList(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int size
    ) {
        return accountService.findList(page, size);
    }

    @PutMapping("{actNo}/transfer-limit")
    AccountResponse transferLimit(@PathVariable String actNo, @RequestBody AccountTransferRequest accountTransferRequest){
        return accountService.transferLimitByActNo(actNo, accountTransferRequest);
    }
}
