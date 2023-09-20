package ru.wevioz.trademarkapi.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class DetailDto {
    private String name;
    private String company;
    private String phone;
    private String address;
}
