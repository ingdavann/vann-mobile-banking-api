package co.istad.vannbankingapi.features.accounttype;

import co.istad.vannbankingapi.domain.AccountType;
import co.istad.vannbankingapi.features.accounttype.dto.AccountTypeResponse;
import co.istad.vannbankingapi.mapper.AccountTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService{
    private final AccountTypeRepository accountTypeRepository;
    private final AccountTypeMapper accountTypeMapper;
    @Override
    public List<AccountTypeMapper> findList() {
        List<AccountType> accountTypes = accountTypeRepository.findAll();
        return accountTypes.stream()
                .map(accountType -> accountTypeMapper).toList();
    }

    @Override
    public AccountTypeResponse findByAlias(String alias) {
        String lowerCaseAlias = alias.toLowerCase();
        if (!accountTypeRepository.existsByAlias(lowerCaseAlias)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Account type has been not found"
            );
        }
        AccountType accountType = accountTypeRepository.findByAlias(lowerCaseAlias);
        return accountTypeMapper.toAccountTypeResponse(accountType);
    }
}
