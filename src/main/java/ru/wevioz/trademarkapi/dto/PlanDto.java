package ru.wevioz.trademarkapi.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PlanDto {
    private String type;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate endDate;
}
