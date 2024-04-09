package co.istad.vannbankingapi.features.transaction;

import co.istad.vannbankingapi.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
