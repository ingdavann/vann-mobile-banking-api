package co.istad.vannbankingapi.features.account;

import co.istad.vannbankingapi.domain.Account;
import co.istad.vannbankingapi.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

}
