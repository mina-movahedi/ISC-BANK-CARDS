package ir.isc.bankcards.controller;

import ir.isc.bankcards.entity.Person;
import ir.isc.bankcards.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // It was better to have a PersonDto too and use it for savePerson() arg instead of person.
    @PostMapping(value = "/savePerson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> savePerson(@RequestBody Person person){
        try {
            Person savedPerson = personService.savePerson(person);
            if(savedPerson == null || savedPerson.getId() == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("MelliCode must have exactly 10 digits.");
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("The person has been successfully saved");
        }catch (Exception e) {
            e.printStackTrace();
            log.error("An error occurred and the person could not be saved.");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("The person could not be saved. Maybe it is duplicated or something else.");
        }
    }
}
