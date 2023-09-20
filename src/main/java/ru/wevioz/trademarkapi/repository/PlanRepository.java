package ru.wevioz.trademarkapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.wevioz.trademarkapi.entity.Detail;
import ru.wevioz.trademarkapi.entity.Plan;

@Repository
public interface PlanRepository extends CrudRepository<Plan, Long> {
}
