package ir.isc.bankcards.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CARD", uniqueConstraints = {@UniqueConstraint(columnNames = {"issuer_Code", "card_Type", "card_Number"})})
@Data
public class Card {

    @Id
    @Column(name = "CARD_NUMBER", length = 16)
    private String cardNumber;

    @Column(name = "ISSUER_CODE", length = 6)
    private int issuerCode;

    @Column(name = "ISSUER_NAME")
    private String issuerName;

    @Column(name = "CARD_TYPE")
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER")
    private Person owner;

    @Column(name = "IS_ACTIVE")
    private boolean isActive;

    @Column(name = "EXPIRATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

}
