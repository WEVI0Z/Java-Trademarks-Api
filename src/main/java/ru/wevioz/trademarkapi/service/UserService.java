package ru.wevioz.trademarkapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.wevioz.trademarkapi.dto.UserRegisterDto;
import ru.wevioz.trademarkapi.dto.UserRegisterRequestDto;

@Service
@RequiredArgsConstructor
public class UserService {
    public UserRegisterDto register(UserRegisterRequestDto dto) {
        return new UserRegisterDto("12353413243");
    }
}
