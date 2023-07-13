package ru.wevioz.trademarkapi.controller;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.wevioz.trademarkapi.dto.TrademarkDto;
import ru.wevioz.trademarkapi.dto.TrademarkShortDto;
import ru.wevioz.trademarkapi.service.TrademarkService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/trademark")
@RequiredArgsConstructor
public class TrademarkController {
    @Autowired private TrademarkService trademarkService;

    @Operation(summary = "Fill the Database with local trademarks", description = "Returns all the added trademarks")
    @ApiResponse(responseCode = "200", description = "Success")
    @PostMapping("/fill")
    public List<TrademarkDto> fill() throws IOException {
        return trademarkService.fillDb();
    }

    @Operation(summary = "Get all the trademarks", description = "Returns all the trademarks")
    @ApiResponse(responseCode = "200", description = "Success")
    @GetMapping()
    public List<TrademarkDto> getAll() {
        return trademarkService.getAll();
    }

    @Operation(summary = "Get the trademark by id", description = "Returns the trademark")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found - the trademark was not found")
    })
    @GetMapping("/{id}")
    public TrademarkDto get(@PathVariable int id) {
        return trademarkService.getById(id);
    }

    @Operation(summary = "Get the trademark by MarkFeature field", description = "Returns the trademark")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found - the trademark was not found")
    })
    @GetMapping("/mark/{mark}")
    public TrademarkDto getByNumber(@PathVariable String mark) {
        return trademarkService.getByMark(mark);
    }

    @Operation(summary = "Find the closest trademarks by MarkFeature field", description = "Returns the trademarks list")
    @ApiResponse(responseCode = "200", description = "Success")
    @GetMapping("/mark/find/{mark}")
    public List<TrademarkDto> findByMark(@PathVariable String mark) {
        return trademarkService.findByMark(mark);
    }

    @Operation(summary = "Get all the trademarks in a short form", description = "Returns all the trademarks in a short form")
    @ApiResponse(responseCode = "200", description = "Success")
    @GetMapping("/short")
    public List<TrademarkShortDto> findByMark() {
        return trademarkService.getShortTrademarks();
    }
}
