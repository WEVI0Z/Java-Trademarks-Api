package ru.wevioz.trademarkapi.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClassDescriptionDto {
    @JacksonXmlProperty(localName = "ClassNumber")
    @JsonAlias("ClassNumber")
    private int classNumber;

    @JacksonXmlProperty(localName = "GoodsServicesDescription")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonAlias("GoodsServicesDescription")
    private List<GoodsServicesDescriptionDto> goodsServicesDescriptions;
}
