package ru.wevioz.trademarkapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.wevioz.trademarkapi.entity.*;
import ru.wevioz.trademarkapi.exception.NotFoundException;
import ru.wevioz.trademarkapi.repository.TrademarkRepository;
import ru.wevioz.trademarkapi.dto.TrademarkDto;
import ru.wevioz.trademarkapi.mapper.TrademarkMapper;
import ru.wevioz.trademarkapi.repository.WordMarkSpecificationRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrademarkService {
    @Autowired private TrademarkMapper trademarkMapper;
    @Autowired private TrademarkRepository trademarkRepository;
    @Autowired private WordMarkSpecificationRepository wordMarkSpecificationRepository;

    @Transactional
    public List<TrademarkDto> fillDb() throws IOException {
        List<TrademarkDto> trademarkDtos = new ArrayList<>();

        trademarkDtos.add(parseLocalFile("src/main/resources/xmls/018173419.xml"));
        trademarkDtos.add(parseLocalFile("src/main/resources/xmls/018173420.xml"));
        trademarkDtos.add(parseLocalFile("src/main/resources/xmls/018173427.xml"));
        trademarkDtos.add(parseLocalFile("src/main/resources/xmls/018173428.xml"));

        List<Trademark> trademarks = trademarkMapper.toEntityList(trademarkDtos);

        trademarks = trademarks.stream().map(this::createConnections).toList();

        saveAll(trademarks);

        return trademarkDtos;
    }

    private void saveAll(List<Trademark> trademarks) {
        trademarkRepository.saveAll(trademarks);
    }

    public List<TrademarkDto> getAll() {
        Iterable<Trademark> trademarks = trademarkRepository.findAll();

        return trademarkMapper.toGetDtoList(trademarks);
    };

    public TrademarkDto getById(int id) {
        Optional<Trademark> trademark = trademarkRepository.findById((long) id);

        if (trademark.isEmpty()) {
            throw new NotFoundException("id");
        }

        return trademarkMapper.toDto(trademark.get());
    }

    public TrademarkDto getByMark(String mark) {
        Optional<Trademark> trademark = trademarkRepository.findTrademarkByMark(mark);

        if (trademark.isEmpty()) {
            throw new NotFoundException("mark");
        }

        return trademarkMapper.toDto(trademark.get());
    }

    public List<TrademarkDto> findByMark(String mark) {
        Iterable<Trademark> trademarks = trademarkRepository.findTrademarksByMarkLike(mark);

        return trademarkMapper.toGetDtoList(trademarks);
    }

    private Trademark createConnections(Trademark trademark) {
        WordMarkSpecification wordMarkSpecification = trademark.getWordMarkSpecification();
        GoodsServicesDetail goodsServicesDetail = trademark.getGoodsServicesDetails();
        GoodsService goodsService = goodsServicesDetail.getGoodsService();
        ClassDescriptionDetail classDescriptionDetail = goodsService.getClassDescriptionDetail();
        List<ClassDescription> classDescriptions = classDescriptionDetail.getClassDescriptions();

        wordMarkSpecification.setTrademark(trademark);
        goodsServicesDetail.setTrademark(trademark);
        goodsService.setGoodsServicesDetail(goodsServicesDetail);
        classDescriptionDetail.setGoodsService(goodsService);

        classDescriptions.forEach(classDescription -> {
            classDescription.setClassDescriptionDetail(classDescriptionDetail);

            List<GoodsServicesDescription> goodsServicesDescriptions = classDescription.getGoodsServicesDescriptions();

            goodsServicesDescriptions.forEach(goodsServicesDescription -> goodsServicesDescription.setClassDescription(classDescription));
        });

        return trademark;
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
