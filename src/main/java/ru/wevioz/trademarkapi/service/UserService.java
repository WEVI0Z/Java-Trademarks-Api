package ru.wevioz.trademarkapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.wevioz.trademarkapi.dto.*;

@Service
@RequiredArgsConstructor
public class UserService {
    public UserRegisterDto register(UserRegisterRequestDto dto) {
        return new UserRegisterDto("12353413243");
    }
    public UserLoginResponseDto login(UserLoginRequestDto dto) {
        return new UserLoginResponseDto("12353413243");
    }
}
