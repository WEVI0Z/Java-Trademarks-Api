package ru.wevioz.trademarkapi.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsServiceDto {
    @JsonAlias("ClassificationVersion")
    private int classificationVersion;

    @JsonAlias("ClassDescriptionDetails")
    private ClassDescriptionDetailDto classDescriptionDetail;
}
