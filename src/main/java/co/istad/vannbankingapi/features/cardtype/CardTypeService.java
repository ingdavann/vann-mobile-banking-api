package co.istad.vannbankingapi.features.cardtype;

import co.istad.vannbankingapi.features.cardtype.dto.CardTypeResponse;

import java.util.List;

public interface CardTypeService {
    List<CardTypeResponse> findList();
    CardTypeResponse findByName(String name);
}
