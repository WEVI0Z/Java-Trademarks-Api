package ru.wevioz.trademarkapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.wevioz.trademarkapi.dto.TrademarkDto;
import ru.wevioz.trademarkapi.dto.WordMarkSpecificationDto;
import ru.wevioz.trademarkapi.entity.Trademark;
import ru.wevioz.trademarkapi.entity.WordMarkSpecification;

@Mapper(componentModel = "spring", uses = TrademarkMapper.class)
public interface WordMarkSpecificationMapper {
    WordMarkSpecification toEntity(WordMarkSpecificationDto dto);

    WordMarkSpecificationDto toDto(WordMarkSpecification entity);
}
