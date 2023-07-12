package ru.wevioz.trademarkapi.controller;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.wevioz.trademarkapi.dto.TrademarkDto;
import ru.wevioz.trademarkapi.service.TrademarkService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/trademark")
@RequiredArgsConstructor
public class TrademarkController {
    @Autowired private TrademarkService trademarkService;

    @PostMapping("/fill")
    public List<TrademarkDto> fill() throws IOException {
        return trademarkService.fillDb();
    }
}
