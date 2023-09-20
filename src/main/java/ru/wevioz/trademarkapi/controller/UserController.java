package ru.wevioz.trademarkapi.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.wevioz.trademarkapi.dto.*;
import ru.wevioz.trademarkapi.entity.Bot;
import ru.wevioz.trademarkapi.entity.Plan;
import ru.wevioz.trademarkapi.service.UserService;

// User controller that says what can user do
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired private UserService userService;

    @PostMapping("/register")
    public UserRegisterDto register(@RequestBody @Valid UserRegisterRequestDto dto) {
        return userService.register(dto);
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto dto) {
        return userService.login(dto);
    }

    @GetMapping("auth")
    public Integer auth(@RequestHeader("token") String token) {
        return userService.auth(token);
    }

    @GetMapping("details")
    public DetailDto getDetails(@RequestHeader("token") String token){
        return userService.getDetails(token);
    }

    @PostMapping("details")
    public DetailDto setDetails(@RequestHeader("token") String token, @RequestBody DetailDto dto){
        return userService.setDetails(token, dto);
    }

    @GetMapping("plan")
    public PlanDto getPlane(@RequestHeader("token") String token){
        return userService.getPlan(token);
    }

    @PostMapping("plan")
    public PlanDto changePlan(@RequestHeader("token") String token, @RequestBody PlanChangeDto dto){
        return userService.changePlan(token, dto);
    }

    @PostMapping("bot")
    public BotResponseDto createBot(@RequestHeader("token") String token, @RequestBody BotRequestDto dto) {
        return userService.createBot(token, dto);
    }

    @GetMapping("bot/{botId}")
    public Bot getBot(@RequestHeader("token") String token, @PathVariable Long botId) {
        return userService.getBot(token, botId);
    }

    @GetMapping("bot/{botId}/name/{name}")
    public Bot changeBotName(@RequestHeader("token") String token, @PathVariable Long botId, @PathVariable String name) {
        return userService.changeBotName(token, botId, name);
    }

    @PostMapping("bot/{botId}/start")
    public Bot startBot(@RequestHeader("token") String token, @PathVariable Long botId) {
        return userService.startBot(token, botId);
    }

    @PostMapping("bot/{botId}/stop")
    public Bot stopBot(@RequestHeader("token") String token, @PathVariable Long botId) {
        return userService.stopBot(token, botId);
    }
}
