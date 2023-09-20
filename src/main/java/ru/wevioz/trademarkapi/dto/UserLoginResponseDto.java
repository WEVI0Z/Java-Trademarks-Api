package ru.wevioz.trademarkapi.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserLoginResponseDto extends TokenResponseDto {
    public UserLoginResponseDto(String token) {
        super(token);
    }
}
