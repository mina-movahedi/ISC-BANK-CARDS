package ir.isc.bankcards.controller;

import ir.isc.bankcards.entity.Card;
import ir.isc.bankcards.model.CardRequest;
import ir.isc.bankcards.model.CardResponse;
import ir.isc.bankcards.model.Status;
import ir.isc.bankcards.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping(value = "/getCard", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardResponse> getCard(@RequestBody CardRequest request) {
        System.out.println("In the name of Allah.");
        CardResponse cardResponse = cardService.getCardResponse(request.getCardNumber(), request.getMelliCode());
        if(cardResponse.getStatus() != null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cardResponse);
        }
        cardResponse.setStatus(Status.ACCEPTED);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cardResponse);
    }


    // It was better to have a cardDto too.

    @PostMapping(value = "/saveCard", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveCard(@RequestBody Card card){
        try {
            cardService.saveCard(card);
        }catch (Exception e) {
            e.printStackTrace();
            log.error("An error occurred and the card could not be saved.");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("The card could not be saved. Maybe it is duplicated or something else.");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("The Card has been successfully saved");
    }

}