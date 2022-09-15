package ir.isc.bankcards.model;

import ir.isc.bankcards.entity.Card;
import lombok.Data;

import java.util.List;

@Data
public class CardsDto {

    List<Card> cardList;
}
