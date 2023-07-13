package ru.wevioz.trademarkapi.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClassDescriptionDto {
    @JsonAlias("ClassNumber")
    private int classNumber;

    @JsonAlias("GoodsServicesDescription")
    private List<GoodsServicesDescriptionDto> goodsServicesDescriptions;
}
