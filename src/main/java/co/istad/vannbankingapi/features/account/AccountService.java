package co.istad.vannbankingapi.features.account;

import co.istad.vannbankingapi.features.account.dto.AccountCreateRequest;
import co.istad.vannbankingapi.features.account.dto.AccountResponse;

public interface AccountService {
    void createNew(AccountCreateRequest accountCreateRequest);

    AccountResponse findByActNo(String actNo);
}
