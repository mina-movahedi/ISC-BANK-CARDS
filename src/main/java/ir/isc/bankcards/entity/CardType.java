package ir.isc.bankcards.entity;

public enum CardType {
    CASH(1), CREDIT(2);

    private int value;

    CardType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CardType getCardType(int val){
        return values()[val + 1];
    }
}
