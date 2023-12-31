package ru.wevioz.trademarkapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserRegisterDto extends TokenResponseDto {
    public UserRegisterDto(String token) {
        super(token);
    }
}
