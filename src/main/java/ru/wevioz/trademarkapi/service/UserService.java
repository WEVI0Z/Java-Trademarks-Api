package ru.wevioz.trademarkapi.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.wevioz.trademarkapi.dto.*;
import ru.wevioz.trademarkapi.entity.Token;
import ru.wevioz.trademarkapi.entity.User;
import ru.wevioz.trademarkapi.repository.TokenRepository;
import ru.wevioz.trademarkapi.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired UserRepository userRepository;
    @Autowired TokenRepository tokenRepository;

    public UserRegisterDto register(UserRegisterRequestDto dto) {
        User user = new User();
        user.setPassword(dto.getPassword());
        user.setLogin(dto.getLogin());

        String token = JWT.create().sign(Algorithm.HMAC256("gthiosgregsdth"));
        Token tokenEntity = new Token();
        tokenEntity.setToken(token);
        tokenEntity.setUser(user);

        tokenRepository.save(tokenEntity);
        userRepository.save(user);

        return new UserRegisterDto(token);
    }
    public UserLoginResponseDto login(UserLoginRequestDto dto) {

        return new UserLoginResponseDto("12353413243");
    }
}
