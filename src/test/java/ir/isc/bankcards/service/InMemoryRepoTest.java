package ir.isc.bankcards.service;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryRepoTest {


    @Test
    void putCard() {
        String[] sampleMelliCodes = {"0012365478", "123sjdn098", "2198745632"};
        for (String sampleMelliCode : sampleMelliCodes) {

            if (InMemoryRepo.getCardToPersonMap().containsKey(sampleMelliCode)) {
                int initialCardsSize = InMemoryRepo.getCardToPersonMap().get(sampleMelliCode).size();

                InMemoryRepo.putCard(sampleMelliCode, new String[]{"cash", "1234", "2021654959263015"});
                assertEquals(initialCardsSize, InMemoryRepo.getCardToPersonMap().get(sampleMelliCode).size());

                InMemoryRepo.putCard(sampleMelliCode, new String[]{"cash", "123456", "20216549592015"});
                assertEquals(initialCardsSize, InMemoryRepo.getCardToPersonMap().get(sampleMelliCode).size());

                InMemoryRepo.putCard(sampleMelliCode, new String[]{"card", "123456", "2021654959263015"});
                assertEquals(initialCardsSize, InMemoryRepo.getCardToPersonMap().get(sampleMelliCode).size());

                String[] card = InMemoryRepo.getCardToPersonMap().get(sampleMelliCode).get(0);

                InMemoryRepo.putCard(sampleMelliCode, new String[]{card[0], card[1], "4721654959263015"});
                assertEquals(initialCardsSize, InMemoryRepo.getCardToPersonMap().get(sampleMelliCode).size());

                InMemoryRepo.putCard(sampleMelliCode, new String[]{"123456", "2021654959263015"});
                assertEquals(initialCardsSize, InMemoryRepo.getCardToPersonMap().get(sampleMelliCode).size());

                InMemoryRepo.putCard(sampleMelliCode, new String[]{"cash", "123456", "2021654959263015"});
                assertEquals(initialCardsSize + 1, InMemoryRepo.getCardToPersonMap().get(sampleMelliCode).size());

            } else {
                int initialSize = InMemoryRepo.getCardToPersonMap().size();

                    Map<String, ArrayList<String[]>> finalCards = InMemoryRepo.putCard(sampleMelliCode, new String[]{"1", "2"});
                    assertEquals(initialSize, finalCards.size());

                    InMemoryRepo.putCard(sampleMelliCode, new String[]{"cash", "1234", "2021654959263015"});
                    assertEquals(initialSize, InMemoryRepo.getCardToPersonMap().size());

                    InMemoryRepo.putCard(sampleMelliCode, new String[]{"cash", "123456", "20216549592015"});
                    assertEquals(initialSize, InMemoryRepo.getCardToPersonMap().size());

                    InMemoryRepo.putCard(sampleMelliCode, new String[]{"card", "123456", "2021654959263015"});
                    assertEquals(initialSize, InMemoryRepo.getCardToPersonMap().size());

                    InMemoryRepo.putCard(sampleMelliCode, new String[]{"123456", "2021654959263015"});
                    assertEquals(initialSize, InMemoryRepo.getCardToPersonMap().size());
                if (!sampleMelliCode.matches("^\\d{10}$")) {
                    InMemoryRepo.putCard(sampleMelliCode, new String[]{"cash", "123456", "2021654959263015"});
                    assertEquals(initialSize, InMemoryRepo.getCardToPersonMap().size());
                } else {
                    InMemoryRepo.putCard(sampleMelliCode, new String[]{"CREDIT", "123456", "2021654959983015"});
                    assertEquals(initialSize + 1, InMemoryRepo.getCardToPersonMap().size());

                }
            }
        }
    }
}