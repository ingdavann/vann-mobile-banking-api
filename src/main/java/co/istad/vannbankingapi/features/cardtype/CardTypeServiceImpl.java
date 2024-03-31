package co.istad.vannbankingapi.features.cardtype;

import co.istad.vannbankingapi.domain.AccountType;
import co.istad.vannbankingapi.domain.CardType;
import co.istad.vannbankingapi.features.cardtype.dto.CardTypeResponse;
import co.istad.vannbankingapi.mapper.CardTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardTypeServiceImpl implements CardTypeService{
    private final CardTypeRepository cardTypeRepository;
    private final CardTypeMapper cardTypeMapper;
    @Override
    public List<CardTypeResponse> findList() {
        List<CardType> cardTypes = cardTypeRepository.findAll();
        return cardTypes.stream()
                .map(cardTypeMapper::toCardTypeResponse).toList();
    }

    @Override
    public CardTypeResponse findByName(String name) {
        String lowerCaseName = name.toLowerCase();
        if (!cardTypeRepository.existsByName(lowerCaseName)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Account type has been not found"
            );
        }
        CardType cardType = cardTypeRepository.findByName(lowerCaseName);
        return cardTypeMapper.toCardTypeResponse(cardType);
    }
}
