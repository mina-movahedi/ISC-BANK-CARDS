package ir.isc.bankcards.service;

import ir.isc.bankcards.entity.Card;
import ir.isc.bankcards.entity.CardDto;
import ir.isc.bankcards.entity.CardType;
import ir.isc.bankcards.entity.Person;
import ir.isc.bankcards.model.CardResponse;
import ir.isc.bankcards.model.Status;
import ir.isc.bankcards.repository.CardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Card getCardByPersonAndNumber(Person owner, String cardNumber) {
        try {
            return cardRepository.findByOwnerAndCardNumber(owner, cardNumber);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("An error occurred during fetching card from data base.");
        }
        return new Card();
    }

    public CardResponse getCardResponse(String cardNumber, String melliCode) {
        Card card;
        CardResponse cardResponse = new CardResponse();
        Person person = personService.getPersonByMelliCode(melliCode);
        if (person == null || person.getId() == null) {
            cardResponse.setStatus(Status.MELLI_CODE_NOT_FOUND);
            return cardResponse;
        }
        card = getCardByPersonAndNumber(person, cardNumber);
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

    public Card cardDtoToCard(CardDto cardDto){
        Person personByMelliCode;
        try {
            personByMelliCode = personService.getPersonByMelliCode(cardDto.getOwnerMelliCode());
            if(personByMelliCode == null || personByMelliCode.getId() == null) {
                log.error("There is no person with this melliCode.");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Person could not be fetched.");
            return null;
        }
        Card card = new Card();
        card.setCardNumber(cardDto.getCardNumber());
        card.setIssuerCode(cardDto.getIssuerCode());
        card.setIssuerName(cardDto.getIssuerName());
        card.setAccountNumber(cardDto.getAccountNumber());
        card.setActive(cardDto.getIsActive() == 1);
        card.setExpirationDate(cardDto.getExpirationDate());
        card.setOwner(personByMelliCode);

        card.setCardType(CardType.getCardType(cardDto.getCardType()));
        return card;
    }

    public Card saveCard(CardDto cardDto) {
        if(!cardDto.getCardNumber().matches("\\d{16}")) {
            log.error("card number must have exactly 16 digits");
        } else if(!String.valueOf(cardDto.getIssuerCode()).matches("\\d{6}")) {
            log.error("issuerCode must have exactly 6 digits.");
        }
        else{
            Card card = cardDtoToCard(cardDto);
            if(card != null && card.getCardNumber() != null) {
                return cardRepository.save(card);
            }
        }
        return null;
    }

    public List<Card> getAllCards(String melliCode){
        System.out.println("melliCode: " + melliCode);
        List<Card> allByMelliCode;
        try{
            allByMelliCode = cardRepository.findAllCardsByMelliCode(melliCode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return allByMelliCode;
    }

    public CardDto cardToCardDto(Card card){
        if(card ==null || card.getCardNumber() == null) {
            log.error("There is an error with card..");
            return null;
        }
        CardDto cardDto = new CardDto();
        cardDto.setCardNumber(card.getCardNumber());
        cardDto.setIssuerCode(card.getIssuerCode());
        cardDto.setIssuerName(card.getIssuerName());
        cardDto.setAccountNumber(card.getAccountNumber());
        cardDto.setIsActive(cardDto.getIsActive());
        cardDto.setExpirationDate(card.getExpirationDate());
        cardDto.setOwnerMelliCode(card.getOwner().getMelliCode());

        card.setCardType(CardType.getCardType(cardDto.getCardType()));
        return cardDto;
    }

}
