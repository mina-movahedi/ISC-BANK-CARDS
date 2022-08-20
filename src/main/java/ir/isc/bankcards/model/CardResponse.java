package ir.isc.bankcards.model;

import lombok.Data;

@Data
public class CardResponse {

    String cardNumber;
    String issuerName;
    int issuerCode;
    String ownerName;
    String ownerFamily;
    Status status;

}
