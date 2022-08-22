package ir.isc.bankcards.service;

import ir.isc.bankcards.entity.Card;
import ir.isc.bankcards.entity.Person;
import ir.isc.bankcards.model.CardResponse;
import ir.isc.bankcards.model.Status;
import ir.isc.bankcards.repository.CardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CardService {

    private final CardRepository cardRepository;
    private final PersonService personService;


    @Autowired
    public CardService(CardRepository cardRepository, PersonService personService) {
        this.cardRepository = cardRepository;
        this.personService = personService;
    }

    public Card getCardByPersonAndNumber(Long personId, String cardNumber) {
        try {
            return cardRepository.findByOwnerAndCardNumber(personId, cardNumber);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("An error occurred during fetching card from data base.");
        }
        return new Card();
    }

    public CardResponse getCardResponse(String cardNumber, String melliCode) {
        Card card;
        CardResponse cardResponse = new CardResponse();
        Person person = personService.getPerson(melliCode);
        if (person == null || person.getId() == null) {
            cardResponse.setStatus(Status.MELLI_CODE_NOT_FOUND);
            return cardResponse;
        }
        card = getCardByPersonAndNumber(person.getId(), cardNumber);
        if (card == null || card.getCardNumber() == null) {
            cardResponse.setStatus(Status.CARD_NUMBER_NOT_FOUND);
            return cardResponse;
        }
        cardResponse.setCardNumber(cardNumber);
        cardResponse.setIssuerName(card.getIssuerName());
        cardResponse.setIssuerCode(card.getIssuerCode());
        cardResponse.setIssuerName(card.getIssuerName());
        cardResponse.setOwnerName(person.getName());
        cardResponse.setOwnerFamily(person.getFamily());
        return cardResponse;
    }

    public void saveCard(Card card) {
        if(!card.getCardNumber().matches("\\d{16}")) {
            log.error("card number must have exactly 16 digits");
        } else if(!String.valueOf(card.getIssuerCode()).matches("\\d{6}")) {
            log.error("issuerCode must have exactly 6 digits.");
        }
        else
        cardRepository.save(card);

    }

}
