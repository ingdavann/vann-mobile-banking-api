package co.istad.vannbankingapi.features.account;

import co.istad.vannbankingapi.features.account.dto.AccountCreateRequest;
import co.istad.vannbankingapi.features.account.dto.AccountNameRequest;
import co.istad.vannbankingapi.features.account.dto.AccountResponse;
import co.istad.vannbankingapi.features.account.dto.AccountTransferRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Page<AccountResponse> findList(int page, int size);
    void hiddenAccount(String actNo);

    void createNew(AccountCreateRequest accountCreateRequest);

    AccountResponse findByActNo(String actNo);

    AccountResponse renameByActNo(String actNo, AccountNameRequest accountNameRequest);

    AccountResponse transferLimitByActNo(String actNo, AccountTransferRequest accountTransferRequest);
}
