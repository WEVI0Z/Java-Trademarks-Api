package ru.wevioz.trademarkapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.wevioz.trademarkapi.entity.ClassDescriptionDetail;
import ru.wevioz.trademarkapi.entity.Trademark;

@Repository
public interface ClassDescriptionDetailRepository extends CrudRepository<ClassDescriptionDetail, Long> {
}