package ir.isc.bankcards.repository;

import ir.isc.bankcards.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    Person findByMelliCode(String melliCode);

}
