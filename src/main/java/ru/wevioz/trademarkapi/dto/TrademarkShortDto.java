package ru.wevioz.trademarkapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class TrademarkShortDto {
    private int applicationNumber;
    private String kindMark;
    private WordMarkSpecificationDto wordMarkSpecification;
}
