package ru.wevioz.trademarkapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.wevioz.trademarkapi.entity.*;
import ru.wevioz.trademarkapi.exception.InvalidFileException;
import ru.wevioz.trademarkapi.exception.NotFoundException;
import ru.wevioz.trademarkapi.repository.TrademarkRepository;
import ru.wevioz.trademarkapi.dto.TrademarkDto;
import ru.wevioz.trademarkapi.mapper.TrademarkMapper;
import ru.wevioz.trademarkapi.repository.WordMarkSpecificationRepository;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TrademarkService {
    private final String MAIN_PATH = "src/main/resources/xmls";

    @Autowired private TrademarkMapper trademarkMapper;
    @Autowired private TrademarkRepository trademarkRepository;
    @Autowired private WordMarkSpecificationRepository wordMarkSpecificationRepository;

    @Transactional
    public List<TrademarkDto> fillDb() throws IOException {
        List<TrademarkDto> trademarkDtos = new ArrayList<>();
        List<String> paths = getListFilesForFolder(new File(MAIN_PATH));

        paths.forEach(path -> {
            try {
                trademarkDtos.add(parseLocalFile(new File(MAIN_PATH + "/" + path)));
            } catch (Exception e) {
                return;
            }
        });

        List<Trademark> trademarks = trademarkMapper.toEntityList(trademarkDtos);

        trademarks = trademarks.stream().map(this::createConnections).toList();

        saveAll(trademarks);

        return trademarkDtos;
    }

    private List<String> getListFilesForFolder(File folder) {
        List<String> paths = new ArrayList<>();

        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            paths.add(fileEntry.getName());
        }

        return paths;
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

    private TrademarkDto parseLocalFile(File file) throws IOException, InvalidFileException {
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode node = xmlMapper.readTree(file);
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode currentNode = node
                .path("TradeMarkTransactionBody")
                .path("TransactionContentDetails")
                .path("TransactionData")
                .path("TradeMarkDetails")
                .path("TradeMark");

        if (!checkMarkFeature(currentNode)) {
            throw new InvalidFileException();
        }

        try {
            return objectMapper.readValue(currentNode.toString(), TrademarkDto.class);
        } catch (Exception e) {
            throw new InvalidFileException();
        }
    }

    private boolean checkMarkFeature(JsonNode node) {
        return node
                .path("MarkFeature")
                .textValue()
                .contains("Word");
    }
}
