package ru.wevioz.trademarkapi.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordMarkSpecificationDto {
    @JacksonXmlProperty(localName = "MarkVerbalElementText")
    @JsonAlias("MarkVerbalElementText")
    private String markVerbalElementText;
}
