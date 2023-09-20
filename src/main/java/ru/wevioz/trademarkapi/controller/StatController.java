package ru.wevioz.trademarkapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.wevioz.trademarkapi.dto.StatisticsDto;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/stat")
@RequiredArgsConstructor
public class StatController {
    @GetMapping
    public StatisticsDto getStat() {
        StatisticsDto statisticsDto = new StatisticsDto();

        statisticsDto.setMessPerDay(76);
        statisticsDto.setHumPerDay(20);
        statisticsDto.setActiveEmpl(new ArrayList<>());

        statisticsDto.getActiveEmpl().add("Alexey");
        statisticsDto.getActiveEmpl().add("Gregory");
        statisticsDto.getActiveEmpl().add("Alexander");
        statisticsDto.getActiveEmpl().add("Vadim");
        statisticsDto.getActiveEmpl().add("Kirill");
        statisticsDto.getActiveEmpl().add("Vladimir");
        statisticsDto.getActiveEmpl().add("Frederik");
        statisticsDto.getActiveEmpl().add("Quocanh");
        statisticsDto.getActiveEmpl().add("Valeria");
        statisticsDto.getActiveEmpl().add("Anton");

        return statisticsDto;
    }
}
