package ru.wevioz.trademarkapi.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsServicesDetailDto {
    @JacksonXmlProperty(localName = "GoodsServices")
    @JsonAlias("GoodsServices")
    private GoodsServiceDto goodsService;
}
