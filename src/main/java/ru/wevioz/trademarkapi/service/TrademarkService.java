package ru.wevioz.trademarkapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;
import ru.wevioz.trademarkapi.dto.TrademarkDto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrademarkService {
    public List<TrademarkDto> fillDb() throws IOException {
        List<TrademarkDto> trademarkDtos = new ArrayList<>();

        trademarkDtos.add(parseLocalFile("src/main/resources/xmls/018173419.xml"));
        trademarkDtos.add(parseLocalFile("src/main/resources/xmls/018173420.xml"));
        trademarkDtos.add(parseLocalFile("src/main/resources/xmls/018173427.xml"));

        return trademarkDtos;
    }

    private TrademarkDto parseLocalFile(String path) throws IOException {
        File file = new File(path);
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode node = xmlMapper.readTree(file);
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode currentNode = node
                .path("TradeMarkTransactionBody")
                .path("TransactionContentDetails")
                .path("TransactionData")
                .path("TradeMarkDetails")
                .path("TradeMark");

        return objectMapper.readValue(currentNode.toString(), TrademarkDto.class);
    }
}
