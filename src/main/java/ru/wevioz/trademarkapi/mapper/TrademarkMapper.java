package ru.wevioz.trademarkapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.wevioz.trademarkapi.dto.TrademarkDto;
import ru.wevioz.trademarkapi.entity.Trademark;

import java.util.List;

@Mapper(componentModel = "spring", uses = {WordMarkSpecificationMapper.class, GoodsServiceDetailMapper.class})
public interface TrademarkMapper {
    Trademark toEntity(TrademarkDto dto);

    TrademarkDto toDto(Trademark entity);

    List<TrademarkDto> toGetDtoList(Iterable<Trademark> iterable);
    List<TrademarkDto> toGetDtoList(List<Trademark> list);
    List<Trademark> toEntityList(List<TrademarkDto> list);
}
