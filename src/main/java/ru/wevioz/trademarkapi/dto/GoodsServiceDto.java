package ru.wevioz.trademarkapi.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "GoodsService")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoodsServiceDto {
    @JacksonXmlProperty(localName = "ClassificationVersion")
    @JsonAlias("ClassificationVersion")
    private int classificationVersion;

    @JacksonXmlProperty(localName = "ClassDescriptionDetails")
    @JsonAlias("ClassDescriptionDetails")
    private ClassDescriptionDetailDto classDescriptionDetail;
}
