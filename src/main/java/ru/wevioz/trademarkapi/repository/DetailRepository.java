package ru.wevioz.trademarkapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.wevioz.trademarkapi.entity.Detail;
import ru.wevioz.trademarkapi.entity.User;

import java.util.Optional;

@Repository
public interface DetailRepository extends CrudRepository<Detail, Long> {
}
