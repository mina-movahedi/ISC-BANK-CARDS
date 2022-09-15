package ir.isc.bankcards.repository;

import ir.isc.bankcards.entity.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card, String> {
    Card findByOwnerAndCardNumber(Long personId, String cardNumber);

    @Query(value = "select c from Card c inner join Person p on c.owner = p.id where p.melli_code =:melliCode", nativeQuery = true)
    List<Card> getAllByMelliCode(@Param("melliCode") String melliCode);
}
