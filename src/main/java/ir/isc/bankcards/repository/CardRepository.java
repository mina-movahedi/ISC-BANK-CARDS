package ir.isc.bankcards.repository;

import ir.isc.bankcards.entity.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, String> {
    Card findByOwnerAndCardNumber(Long personId, String cardNumber);
}
