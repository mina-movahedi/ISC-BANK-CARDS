package ir.isc.bankcards.entity;

import lombok.Data;

import java.util.Date;

@Data
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
