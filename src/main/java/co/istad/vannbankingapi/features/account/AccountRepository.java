package co.istad.vannbankingapi.features.account;

import co.istad.vannbankingapi.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByActNo(String actNo);
    boolean existsByActNo(String actNo);

    @Modifying
    @Query("""
        UPDATE Account AS a
        SET a.isHidden = TRUE
        WHERE a.actNo = ?1
    """)
    void hideAccountByActNo(String actNo);
}
