package ru.wevioz.trademarkapi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.wevioz.trademarkapi.entity.Trademark;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrademarkRepository extends CrudRepository<Trademark, Long> {
    @Query("SELECT trademark FROM Trademark trademark WHERE trademark.wordMarkSpecification.markVerbalElementText = :value")
    Optional<Trademark> findTrademarkByMark(@Param("value") String value);

    @Query("SELECT trademark FROM Trademark trademark WHERE trademark.wordMarkSpecification.markVerbalElementText LIKE %:value%")
    List<Trademark> findTrademarksByMarkLike(@Param("value") String value);
}
