package co.istad.vannbankingapi.mapper;

import co.istad.vannbankingapi.domain.AccountType;
import co.istad.vannbankingapi.features.accounttype.dto.AccountTypeResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountTypeMapper {
    AccountTypeResponse toAccountTypeResponse(AccountType accountType);
}
