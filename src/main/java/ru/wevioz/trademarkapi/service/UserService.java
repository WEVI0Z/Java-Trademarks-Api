package ru.wevioz.trademarkapi.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.wevioz.trademarkapi.dto.*;
import ru.wevioz.trademarkapi.entity.Detail;
import ru.wevioz.trademarkapi.entity.Plan;
import ru.wevioz.trademarkapi.entity.Token;
import ru.wevioz.trademarkapi.entity.User;
import ru.wevioz.trademarkapi.exception.NotFoundException;
import ru.wevioz.trademarkapi.repository.DetailRepository;
import ru.wevioz.trademarkapi.repository.PlanRepository;
import ru.wevioz.trademarkapi.repository.TokenRepository;
import ru.wevioz.trademarkapi.repository.UserRepository;

import java.io.NotActiveException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired UserRepository userRepository;
    @Autowired TokenRepository tokenRepository;
    @Autowired DetailRepository detailRepository;
    @Autowired PlanRepository planRepository;

    public UserRegisterDto register(UserRegisterRequestDto dto) {
        User user = new User();
        user.setPassword(dto.getPassword());
        user.setLogin(dto.getLogin());

        String token = JWT.create().sign(Algorithm.HMAC256(String.valueOf(Math.random())));
        Token tokenEntity = new Token();
        tokenEntity.setToken(token);
        tokenEntity.setUser(user);

        Plan plan = new Plan();
        plan.setType("0");
        plan.setStartDate(LocalDate.now());
        plan.setEndDate(LocalDate.now().plusDays(30));
        plan.setUser(user);

        planRepository.save(plan);
        tokenRepository.save(tokenEntity);
        userRepository.save(user);

        return new UserRegisterDto(token);
    }
    public UserLoginResponseDto login(UserLoginRequestDto dto) {
        Optional<User> user = userRepository.findUserByLoginAndPassword(dto.getLogin(), dto.getPassword());

        if (user.isEmpty()) {
            throw new NotFoundException("user");
        }

        User userEntity = user.get();

        String token = JWT.create().sign(Algorithm.HMAC256(String.valueOf(Math.random())));
        Token tokenEntity = new Token();
        tokenEntity.setToken(token);
        tokenEntity.setUser(userEntity);

        tokenRepository.save(tokenEntity);

        return new UserLoginResponseDto(token);
    }
    public Integer auth(String token) {
        List<Token> tokens = tokenRepository.findAllByToken(token);

        if (tokens.isEmpty()) {
            throw new NotFoundException("token");
        }

        return tokens.get(0).getUser().getId();
    }

    public DetailDto getDetails(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new NotFoundException("token");
        }

        Detail detail = user.get().getDetail();

        if (Objects.isNull(detail)){
            throw new NotFoundException("detail");
        }

        DetailDto dto = new DetailDto();

        dto.setName(detail.getName());
        dto.setPhone(detail.getPhone());
        dto.setCompany(detail.getCompany());
        dto.setAddress(detail.getAddress());

        return dto;
    }

    public DetailDto setDetails(Long id, DetailDto dto) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new NotFoundException("user");
        }

        User userEntity = user.get();

        Detail detail = userEntity.getDetail();

        if (Objects.isNull(detail)) {
            userEntity.setDetail(new Detail());
            detail = userEntity.getDetail();
        }

        detail.setName(Objects.nonNull(dto.getName()) ? dto.getName() : detail.getName());
        detail.setCompany(Objects.nonNull(dto.getCompany()) ? dto.getCompany() : detail.getCompany());
        detail.setPhone(Objects.nonNull(dto.getPhone()) ? dto.getPhone() : detail.getPhone());
        detail.setAddress(Objects.nonNull(dto.getAddress()) ? dto.getAddress() : detail.getAddress());

        userEntity.setDetail(detail);

        detail.setUser(userEntity);
        detailRepository.save(detail);

        return dto;
    }

    public PlanDto getPlan(long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new NotFoundException("token");
        }

        Plan plan = user.get().getPlan();

        if (Objects.isNull(plan)){
            throw new NotFoundException("plan");
        }

        PlanDto dto = new PlanDto();

        dto.setType(plan.getType());
        dto.setEndDate(plan.getEndDate());
        dto.setStartDate(plan.getStartDate());

        return dto;
    }
}
