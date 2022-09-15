package ir.isc.bankcards.controller;

import ir.isc.bankcards.entity.Card;
import ir.isc.bankcards.entity.CardDto;
import ir.isc.bankcards.model.CardRequest;
import ir.isc.bankcards.model.CardResponse;
import ir.isc.bankcards.model.CardsDto;
import ir.isc.bankcards.model.Status;
import ir.isc.bankcards.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    /* Get only one card with some parameters. */
    @PostMapping(value = "/getCard", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardResponse> getCard(@RequestBody CardRequest request) {
        System.out.println("In the name of Allah.");
        CardResponse cardResponse = cardService.getCardResponse(request.getCardNumber(), request.getMelliCode());
        if (cardResponse.getStatus() != null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cardResponse);
        }
        cardResponse.setStatus(Status.ACCEPTED);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cardResponse);
    }

    /* To get all person's cards */
    @GetMapping(value = "/getAllCards/{melliCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardsDto> getAllCards(@PathVariable String melliCode) {
        List<Card> allCards = cardService.getAllCards(melliCode);
        CardsDto cardsDto = new CardsDto();
        cardsDto.setCardList(allCards);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cardsDto);
    }

    /*
    * json sample to save a Card:
    *
          {
           "cardNumber": "1478523045626595",
           "issuerCode": "789654",
           "issuerName" : "aaasss",
           "isActive": 1,
           "expirationDate": "1402-12-01",
           "cardType": 0,
           "ownerMelliCode": "0012365478"
           }
    * */

    @PostMapping(value = "/saveCard", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveCard(@RequestBody CardDto card) {
        try {
            cardService.saveCard(card);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("An error occurred and the card could not be saved.");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("The card could not be saved. Maybe it is duplicated or something else.");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("The Card has been successfully saved");
    }

}