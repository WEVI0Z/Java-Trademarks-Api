package ru.wevioz.trademarkapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class StatisticsDto {
    private int humPerDay;
    private int messPerDay;
    private List<String> activeEmpl;
}
