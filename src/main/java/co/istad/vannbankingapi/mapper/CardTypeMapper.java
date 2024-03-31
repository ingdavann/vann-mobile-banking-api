package co.istad.vannbankingapi.mapper;

import co.istad.vannbankingapi.domain.CardType;
import co.istad.vannbankingapi.features.cardtype.dto.CardTypeResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardTypeMapper{
    CardTypeResponse toCardTypeResponse(CardType cardType);
}
