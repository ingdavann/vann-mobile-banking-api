package co.istad.vannbankingapi.features.cardtype;

import co.istad.vannbankingapi.domain.CardType;
import co.istad.vannbankingapi.features.cardtype.dto.CardTypeResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardTypeRepository extends JpaRepository<CardType, Integer> {
    boolean existsByName(String name);
    CardType findByName(String name);
}
