package ru.wevioz.trademarkapi.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsServicesDescriptionDto {
    @JsonAlias("languageCode")
    private String languageCode;

    @JsonAlias("")
    private String content;
}
