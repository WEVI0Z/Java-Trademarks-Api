package ru.wevioz.trademarkapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.wevioz.trademarkapi.entity.GoodsServicesDescription;
import ru.wevioz.trademarkapi.entity.Trademark;

@Repository
public interface GoodsServicesDescriptionRepository extends CrudRepository<GoodsServicesDescription, Long> {
}
