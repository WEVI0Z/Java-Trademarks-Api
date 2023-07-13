package ru.wevioz.trademarkapi.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TrademarkDto {
    @JsonAlias("ApplicationNumber")
    private int applicationNumber;

    @JsonAlias("KindMark")
    private String kindMark;

    @JsonAlias("MarkFeature")
    private String markFeature;

    @JsonAlias("WordMarkSpecification")
    private WordMarkSpecificationDto wordMarkSpecification;

    @JsonAlias("GoodsServicesDetails")
    private GoodsServicesDetailDto goodsServicesDetails;
}
