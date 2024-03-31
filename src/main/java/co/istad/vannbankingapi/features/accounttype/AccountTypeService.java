package co.istad.vannbankingapi.features.accounttype;

import co.istad.vannbankingapi.features.accounttype.dto.AccountTypeResponse;
import co.istad.vannbankingapi.mapper.AccountTypeMapper;

import java.util.List;

public interface AccountTypeService {
    List<AccountTypeMapper> findList();
    AccountTypeResponse findByAlias(String alias);
}
