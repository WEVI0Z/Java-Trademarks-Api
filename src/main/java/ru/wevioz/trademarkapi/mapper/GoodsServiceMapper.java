package ru.wevioz.trademarkapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.wevioz.trademarkapi.dto.GoodsServiceDto;
import ru.wevioz.trademarkapi.dto.GoodsServicesDetailDto;
import ru.wevioz.trademarkapi.entity.GoodsService;

@Mapper(componentModel = "spring", uses = {GoodsServiceDetailMapper.class, ClasDescriptionDetailMapper.class})
public interface GoodsServiceMapper {
    GoodsService toEntity(GoodsServiceDto dto);

    GoodsServiceDto toDto(GoodsService entity);
}
