package co.istad.vannbankingapi.features.account;

import co.istad.vannbankingapi.features.account.dto.AccountCreateRequest;
import co.istad.vannbankingapi.features.account.dto.AccountResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

}
