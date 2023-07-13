package ru.wevioz.trademarkapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.wevioz.trademarkapi.dto.ClassDescriptionDetailDto;
import ru.wevioz.trademarkapi.dto.ClassDescriptionDto;
import ru.wevioz.trademarkapi.entity.ClassDescription;
import ru.wevioz.trademarkapi.entity.ClassDescriptionDetail;
import ru.wevioz.trademarkapi.entity.GoodsService;

@Mapper(componentModel = "spring", uses = {ClassDescriptionDetail.class, GoodsServicesDescriptionMapper.class})
public interface ClassDescriptionMapper {
    ClassDescription toEntity(ClassDescriptionDto dto);

    ClassDescriptionDto toDto(ClassDescription entity);
}
