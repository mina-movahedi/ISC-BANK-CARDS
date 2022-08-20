package ir.isc.bankcards.service;


// This class is the answer of third and forth items of the Assignment file.

import ir.isc.bankcards.entity.CardType;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class InMemoryRepo {

    // We map a person to his/her cards.

    private static Map<String, ArrayList<String[]>> cardToPersonMap = new HashMap<>();

    public static Map<String, ArrayList<String[]>> getCardToPersonMap() {
        return cardToPersonMap;
    }

    public static ArrayList<String> getAllCardNumbers() {
        ArrayList<String> allCardNos = new ArrayList<>();
        if (cardToPersonMap.size() > 0) {
            for (String melliCode : cardToPersonMap.keySet()) {
                cardToPersonMap.get(melliCode).forEach(a -> allCardNos.add(a[2]));
            }

        }
        return allCardNos;
    }

    public static Map<String, ArrayList<String[]>> putCard(String melliCode, String[] card) {

        if (card.length != 3) {
            log.error("Incorrect card. Three factors should be entered.");
        } else if (!melliCode.matches("^\\d{10}$")) {
            log.error("MelliCodes have exactly 10 digits.");
        } else if (!(card[0].equalsIgnoreCase(CardType.CASH.name()) || card[0].equalsIgnoreCase(CardType.CREDIT.name()))) {
            log.error("Invalid CardType. Enter CASH or CREDIT as the first arg.");
        } else if (card[1].length() != 6) {
            log.error("IssuerCode should be exactly of 6 digits.");
        } else if (card[2].length() != 16 || card[2].startsWith("0")) {
            log.error("Incorrect card number.");
        } else if (getAllCardNumbers().contains(card[2])) {
            
            log.error("card number is duplicated.");
        } else {
            ArrayList<String[]> cards = (cardToPersonMap.get(melliCode) != null) ? cardToPersonMap.get(melliCode) : new ArrayList<>();
            boolean isPermittedToAdd = true;
            if (cards != null && cards.size() > 0) {
                for (String[] savedCard : cardToPersonMap.get(melliCode)) {
                    if (savedCard[0].equalsIgnoreCase(card[0]) && savedCard[1].equals(card[1])) {
                        log.error("This person has another card with this cardType in this issuer!");
                        isPermittedToAdd = false;
                        break;
                    }
                }
            }
            if (isPermittedToAdd) {
                cards.add(card);
                cardToPersonMap.put(melliCode, cards);
            }
        }

        return cardToPersonMap;
    }

    // In order to initialization, we can use this static block or use a text file.
    static {


        putCard("0012365478", new String[]{CardType.CASH.name(), "071078", "6037997145632140"});
        putCard("0012365478", new String[]{CardType.CASH.name(), "123145", "5892997145632140"});
        putCard("0012365478", new String[]{CardType.CREDIT.name(), "123145", "5892997145632140"});
        putCard("0012365478", new String[]{CardType.CASH.name(), "864201", "5892997145632140"});
        putCard("0012365478", new String[]{CardType.CREDIT.name(), "908903", "5892997145632140"});


        putCard("1012365477", new String[]{CardType.CASH.name(), "071078", "6037997145632141"});
        putCard("1012365477", new String[]{CardType.CASH.name(), "123145", "5892997145632141"});
        putCard("1012365477", new String[]{CardType.CREDIT.name(), "123145", "5892997145632141"});
        putCard("1012365477", new String[]{CardType.CASH.name(), "864201", "5892997145632141"});
        putCard("1012365477", new String[]{CardType.CREDIT.name(), "908903", "5892997145632141"});


        putCard("2012365477", new String[]{CardType.CASH.name(), "071078", "6037997145632142"});
        putCard("2012365477", new String[]{CardType.CASH.name(), "123145", "5892997145632142"});
        putCard("2012365477", new String[]{CardType.CREDIT.name(), "123145", "5892997145632142"});
        putCard("2012365477", new String[]{CardType.CASH.name(), "864201", "5892997145632142"});
        putCard("2012365477", new String[]{CardType.CREDIT.name(), "908903", "5892997145632142"});


        putCard("3012365477", new String[]{CardType.CASH.name(), "071078", "6037997145632143"});
        putCard("3012365477", new String[]{CardType.CASH.name(), "123145", "5892997145632143"});
        putCard("3012365477", new String[]{CardType.CREDIT.name(), "123145", "5892997145632143"});
        putCard("3012365477", new String[]{CardType.CASH.name(), "864201", "5892997145632143"});
        putCard("3012365477", new String[]{CardType.CREDIT.name(), "908903", "5892997145632143"});

        System.out.println("Card To Person Map: " + cardToPersonMap);

    }

}
