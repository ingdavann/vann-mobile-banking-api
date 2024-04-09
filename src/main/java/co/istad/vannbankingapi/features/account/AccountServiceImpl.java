package co.istad.vannbankingapi.features.account;

import co.istad.vannbankingapi.domain.Account;
import co.istad.vannbankingapi.domain.AccountType;
import co.istad.vannbankingapi.domain.User;
import co.istad.vannbankingapi.domain.UserAccount;
import co.istad.vannbankingapi.features.account.AccountRepository;
import co.istad.vannbankingapi.features.account.AccountService;
import co.istad.vannbankingapi.features.account.UserAccountRepository;
import co.istad.vannbankingapi.features.account.dto.AccountCreateRequest;
import co.istad.vannbankingapi.features.account.dto.AccountNameRequest;
import co.istad.vannbankingapi.features.account.dto.AccountResponse;
import co.istad.vannbankingapi.features.account.dto.AccountTransferRequest;
import co.istad.vannbankingapi.features.accounttype.AccountTypeRepository;
import co.istad.vannbankingapi.features.user.UserRepository;
import co.istad.vannbankingapi.mapper.AccountMapper;
import co.istad.vannbankingapi.util.RandomUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final UserAccountRepository userAccountRepository;
    private final AccountRepository accountRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;

    @Override
    public Page<AccountResponse> findList(int page, int size) {
        // validate page and size >= 0
        if (page < 0 ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Page must be greater than or equal to zero");
        }
        if (size < 1){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Size must be greater than or equal to one");
        }
        Sort sortByActName = Sort.by(Sort.Direction.ASC, "actName");
        PageRequest pageRequest = PageRequest.of(page, size, sortByActName);
        Page<Account> accounts = accountRepository.findAll(pageRequest);
        return accounts.map(accountMapper::toAccountResponse);
    }

    @Override
    @Transactional
    public void hiddenAccount(String actNo) {
        if (!accountRepository.existsByActNo(actNo)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account has not been found!");
        }
        try{
            accountRepository.hideAccountByActNo(actNo);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong");
        }
    }

    @Override
    public void createNew(AccountCreateRequest accountCreateRequest) {
        //Check Account type
        AccountType accountType = accountTypeRepository.findByAlias(accountCreateRequest.accountTypeAlias())
                .orElseThrow(()->
                            new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "Invalid account type")
                        );
        // check user by UUID
        User user = userRepository.findByUuid(accountCreateRequest.userUuid())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "User has not been found"));

        // map account dto to account entity
        Account account = accountMapper.fromAccountCreateRequest(accountCreateRequest);
        account.setAccountType(accountType);
        account.setActName(user.getName());
        account.setActNo(RandomUtil.generate9Digits());
        account.setTransferLimit(BigDecimal.valueOf(5000));
        account.setIsHidden(false);

        UserAccount userAccount = new UserAccount();
        userAccount.setAccount(account);
        userAccount.setUser(user);
        userAccount.setIsDeleted(false);
        userAccount.setIsBlocked(false);
        userAccount.setCreateAt(LocalDateTime.now());

        userAccountRepository.save(userAccount);

    }

    @Override
    public AccountResponse findByActNo(String actNo) {
        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account no is invalid!"
                ));
        return accountMapper.toAccountResponse(account);
    }

    @Override
    public AccountResponse renameByActNo(String actNo, AccountNameRequest accountNameRequest) {
        // check if exist
        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account has not been found!"
                ));
        if (account.getAlias() != null && account.getAlias().equals(accountNameRequest.newName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "New name must not the same as old name");
        }
        account.setAlias(accountNameRequest.newName());
        account = accountRepository.save(account);
        return accountMapper.toAccountResponse(account);
    }

    @Override
    public AccountResponse transferLimitByActNo(String actNo, AccountTransferRequest accountTransferRequest) {
        // check if exist
        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account has not been found!"
                ));
        account.setTransferLimit(accountTransferRequest.transferLimit());
        account = accountRepository.save(account);
        return accountMapper.toAccountResponse(account);
    }
}
