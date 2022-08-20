package ir.isc.bankcards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
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
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("ir.isc.bankcards")).build();
    }

}
