package co.istad.vannbankingapi.features.user;

import co.istad.vannbankingapi.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
