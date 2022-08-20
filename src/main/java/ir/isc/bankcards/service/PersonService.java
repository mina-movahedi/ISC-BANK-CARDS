package ir.isc.bankcards.service;

import ir.isc.bankcards.entity.Person;
import ir.isc.bankcards.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person getPerson(String melliCode){
        return personRepository.findByMelliCode(melliCode);
    }

    public void savePerson(Person person) {
        personRepository.save(person);
    }

}
