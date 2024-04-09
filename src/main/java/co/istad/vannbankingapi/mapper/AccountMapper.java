package co.istad.vannbankingapi.mapper;

import co.istad.vannbankingapi.domain.Account;
import co.istad.vannbankingapi.domain.User;
import co.istad.vannbankingapi.domain.UserAccount;
import co.istad.vannbankingapi.features.account.dto.AccountCreateRequest;
import co.istad.vannbankingapi.features.account.dto.AccountResponse;
import co.istad.vannbankingapi.features.user.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        UserMapper.class,
        AccountMapper.class
})
public interface AccountMapper {
    Account fromAccountCreateRequest(AccountCreateRequest accountCreateRequest);

    @Mapping(source = "userAccountList", target = "user", qualifiedByName = "mapUserResponse")
    AccountResponse toAccountResponse(Account account);



}
