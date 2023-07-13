package ru.wevioz.trademarkapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.wevioz.trademarkapi.entity.GoodsServicesDetail;
import ru.wevioz.trademarkapi.entity.Trademark;

@Repository
public interface GoodsServicesDetailRepository extends CrudRepository<GoodsServicesDetail, Long> {
}
