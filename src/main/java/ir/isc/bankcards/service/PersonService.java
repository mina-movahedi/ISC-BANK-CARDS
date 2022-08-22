package ir.isc.bankcards.service;

import ir.isc.bankcards.entity.Person;
import ir.isc.bankcards.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person getPerson(String melliCode){
        if(melliCode.matches("^\\d{10}$")) {
        return personRepository.findByMelliCode(melliCode);
    }else log.error("MelliCodes must have exactly 10 digits.");
        return new Person();
    }

    public Person savePerson(Person person) {
        if(person.getMelliCode().matches("^\\d{10}$")) {
            return personRepository.save(person);
        }else {
            log.error("MelliCodes must have exactly 10 digits.");
            return new Person();
        }
    }

}
