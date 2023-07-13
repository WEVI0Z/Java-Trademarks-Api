package ru.wevioz.trademarkapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.wevioz.trademarkapi.dto.GoodsServicesDetailDto;
import ru.wevioz.trademarkapi.entity.GoodsServicesDetail;

@Mapper(componentModel = "spring", uses = {TrademarkMapper.class, GoodsServiceMapper.class})
public interface GoodsServiceDetailMapper {
    GoodsServicesDetail toEntity(GoodsServicesDetailDto dto);

    GoodsServicesDetailDto toDto(GoodsServicesDetail entity);
}
