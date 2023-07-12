package ru.wevioz.trademarkapi.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "TradeMark")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TrademarkDto {
    @JacksonXmlProperty(localName = "ApplicationNumber")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonAlias("ApplicationNumber")
    private int applicationNumber;

    @JacksonXmlProperty(localName = "KindMark")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonAlias("KindMark")
    private String kindMark;

    @JacksonXmlProperty(localName = "MarkFeature")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonAlias("MarkFeature")
    private String markFeature;

    @JacksonXmlProperty(localName = "WordMarkSpecification")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonAlias("WordMarkSpecification")
    private WordMarkSpecificationDto wordMarkSpecification;

    @JacksonXmlProperty(localName = "GoodsServicesDetails")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonAlias("GoodsServicesDetails")
    private GoodsServicesDetailDto goodsServicesDetails;
}
