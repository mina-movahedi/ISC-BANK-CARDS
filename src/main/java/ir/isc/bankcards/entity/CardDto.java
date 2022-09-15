package ir.isc.bankcards.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CardDto {

    private String cardNumber;
    private int issuerCode;
    private String issuerName;
    private int cardType;

    private String ownerMelliCode;

    private short isActive;

    private Date expirationDate;

    private String accountNumber;

}
