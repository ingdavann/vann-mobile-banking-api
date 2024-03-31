package co.istad.vannbankingapi.features.accounttype;

import co.istad.vannbankingapi.features.accounttype.dto.AccountTypeResponse;
import co.istad.vannbankingapi.mapper.AccountTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/account-types")
@RequiredArgsConstructor
public class AccountTypeController {
    private final AccountTypeService accountTypeService;

    @GetMapping
    List<AccountTypeMapper> findList(){
        return accountTypeService.findList();
    }
    @GetMapping("/{alias}")
    AccountTypeResponse findByAlias(@PathVariable String alias){
        return accountTypeService.findByAlias(alias);
    }
}
