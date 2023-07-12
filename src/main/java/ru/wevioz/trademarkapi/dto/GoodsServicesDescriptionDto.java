package ru.wevioz.trademarkapi.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsServicesDescriptionDto {
    @JacksonXmlProperty(isAttribute = true, localName = "languageCode")
    @JsonAlias("languageCode")
    private String languageCode;

    @JacksonXmlText
    @JsonAlias("")
    private String content;
}
