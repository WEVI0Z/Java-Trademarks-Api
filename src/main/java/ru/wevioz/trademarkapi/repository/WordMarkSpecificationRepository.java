package ru.wevioz.trademarkapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.wevioz.trademarkapi.entity.Trademark;
import ru.wevioz.trademarkapi.entity.WordMarkSpecification;

@Repository
public interface WordMarkSpecificationRepository extends CrudRepository<WordMarkSpecification, Long> {
}
