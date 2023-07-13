package ru.wevioz.trademarkapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.wevioz.trademarkapi.entity.GoodsService;
import ru.wevioz.trademarkapi.entity.Trademark;

@Repository
public interface GoodsServiceRepository extends CrudRepository<GoodsService, Long> {
}
