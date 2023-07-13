package ru.wevioz.trademarkapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.wevioz.trademarkapi.dto.ClassDescriptionDto;
import ru.wevioz.trademarkapi.dto.GoodsServicesDescriptionDto;
import ru.wevioz.trademarkapi.entity.ClassDescription;
import ru.wevioz.trademarkapi.entity.ClassDescriptionDetail;
import ru.wevioz.trademarkapi.entity.GoodsServicesDescription;

@Mapper(componentModel = "spring", uses = ClassDescription.class)
public interface GoodsServicesDescriptionMapper {
    GoodsServicesDescription toEntity(GoodsServicesDescriptionDto dto);

    GoodsServicesDescriptionDto toDto(GoodsServicesDescription entity);
}
