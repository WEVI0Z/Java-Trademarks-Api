package ru.wevioz.trademarkapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.wevioz.trademarkapi.dto.ClassDescriptionDetailDto;
import ru.wevioz.trademarkapi.dto.GoodsServiceDto;
import ru.wevioz.trademarkapi.dto.GoodsServicesDetailDto;
import ru.wevioz.trademarkapi.entity.ClassDescriptionDetail;
import ru.wevioz.trademarkapi.entity.GoodsService;

@Mapper(componentModel = "spring", uses = {GoodsServiceMapper.class, ClassDescriptionMapper.class})
public interface ClasDescriptionDetailMapper {
    ClassDescriptionDetail toEntity(ClassDescriptionDetailDto dto);

    ClassDescriptionDetailDto toDto(ClassDescriptionDetail entity);
}
