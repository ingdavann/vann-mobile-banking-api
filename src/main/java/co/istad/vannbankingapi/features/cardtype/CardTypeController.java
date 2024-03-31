package co.istad.vannbankingapi.features.cardtype;

import co.istad.vannbankingapi.domain.CardType;
import co.istad.vannbankingapi.features.cardtype.dto.CardTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/card-types")
public class CardTypeController {
    private final CardTypeService cardTypeService;

    @GetMapping
    List<CardTypeResponse> findList(){
        return cardTypeService.findList();
    }

    @GetMapping("/{name}")
    CardTypeResponse findByName(@PathVariable String name){
        return cardTypeService.findByName(name);
    }
}
