package co.istad.vannbankingapi.features.accounttype;

import co.istad.vannbankingapi.domain.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
    boolean existsByAlias(String alias);
    AccountType findByAlias(String alias);
}
