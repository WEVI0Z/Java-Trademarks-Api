package ru.wevioz.trademarkapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.wevioz.trademarkapi.entity.Bot;
import ru.wevioz.trademarkapi.entity.Plan;

@Repository
public interface BotRepository extends CrudRepository<Bot, Long> {
}
