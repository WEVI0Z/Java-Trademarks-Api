package ru.wevioz.trademarkapi.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.wevioz.trademarkapi.dto.*;
import ru.wevioz.trademarkapi.entity.*;
import ru.wevioz.trademarkapi.exception.NotFoundException;
import ru.wevioz.trademarkapi.repository.*;

import java.io.NotActiveException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Autowired BotRepository botRepository;

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
        List<User> users = userRepository.findUserByLoginAndPassword(dto.getLogin(), dto.getPassword());

        if (Objects.isNull(users)) {
            throw new NotFoundException("user");
        }

        User userEntity = users.get(0);

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

    public DetailDto getDetails(String token) {
        Long id = (long) auth(token);
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

    public DetailDto setDetails(String token, DetailDto dto) {
        Long id = (long) auth(token);
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

    public PlanDto getPlan(String token) {
        Long id = (long) auth(token);
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

    public PlanDto changePlan(String token, PlanChangeDto dto) {
        Long id = (long) auth(token);
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new NotFoundException("user");
        }

        User userEntity = user.get();

        Plan plan = userEntity.getPlan();
        plan.setType(dto.getType());
        plan.setStartDate(LocalDate.now());
        plan.setEndDate(LocalDate.now().plusDays(dto.getDays()));

        plan.setUser(userEntity);
        planRepository.save(plan);

        PlanDto planDto = new PlanDto();
        planDto.setType(plan.getType());
        planDto.setStartDate(plan.getStartDate());
        planDto.setEndDate(plan.getEndDate());

        return planDto;
    }

    public BotResponseDto createBot(String token, BotRequestDto dto) {
        Long id = (long) auth(token);
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new NotFoundException("user");
        }

        User userEntity = user.get();

        Bot bot = new Bot();
        bot.setName(dto.getName());
        bot.setStatus(false);
        bot.setStartTime(null);

        bot.setUser(userEntity);
        botRepository.save(bot);

        BotResponseDto botDto = new BotResponseDto();
        botDto.setBotId((long) bot.getId());

        return botDto;
    }

    public Bot getBot(String token, Long botId) {
        Long id = (long) auth(token);
        Optional<Bot> botOptional = botRepository.findById(botId);

        if (botOptional.isEmpty()) {
            throw new NotFoundException("bot");
        }

        return botOptional.get();
    }

    public Bot startBot(String token, Long botId) {
        Long id = (long) auth(token);
        Optional<Bot> botOptional = botRepository.findById(botId);

        if (botOptional.isEmpty()) {
            throw new NotFoundException("bot");
        }

        Bot bot = botOptional.get();
        bot.setStartTime(LocalDateTime.now());
        bot.setStatus(true);

        botRepository.save(bot);

        return bot;
    }

    public Bot changeBotName(String token, Long botId, String name) {
        Long id = (long) auth(token);
        Optional<Bot> botOptional = botRepository.findById(botId);

        if (botOptional.isEmpty()) {
            throw new NotFoundException("bot");
        }

        Bot bot = botOptional.get();
        bot.setName(name);
        botRepository.save(bot);

        return bot;
    }

    public Bot stopBot(String token, Long botId) {
        Long id = (long) auth(token);
        Optional<Bot> botOptional = botRepository.findById(botId);

        if (botOptional.isEmpty()) {
            throw new NotFoundException("bot");
        }

        Bot bot = botOptional.get();
        bot.setStartTime(null);
        bot.setStatus(false);

        botRepository.save(bot);

        return bot;
    }
}
