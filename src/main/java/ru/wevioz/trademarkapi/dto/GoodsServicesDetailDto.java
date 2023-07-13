package ru.wevioz.trademarkapi.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsServicesDetailDto {
    @JsonAlias("GoodsServices")
    private GoodsServiceDto goodsService;
}
