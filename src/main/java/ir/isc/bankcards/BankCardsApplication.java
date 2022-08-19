package ir.isc.bankcards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankCardsApplication {

    public static void main(String[] args) {
        // ---------------------------------------
        // used memory question
        // I did not know your meaning of start the program and end of it, but we can use this code every where we need.
        long beforeStartWorking = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        // ---------------------------------------
        SpringApplication.run(BankCardsApplication.class, args);

        //----------------------------------------
        // used memory question
        long afterEndWorking = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Memory usage: " + (afterEndWorking - beforeStartWorking));
        //----------------------------------------
    }

}
