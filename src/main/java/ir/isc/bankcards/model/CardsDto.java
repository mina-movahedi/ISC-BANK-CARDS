package ir.isc.bankcards.model;

import ir.isc.bankcards.entity.CardDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CardsDto {

    List<CardDto> cardDtoList;
}
