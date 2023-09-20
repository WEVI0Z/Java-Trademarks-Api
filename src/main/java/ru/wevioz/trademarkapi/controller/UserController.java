package ru.wevioz.trademarkapi.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.wevioz.trademarkapi.dto.*;
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

    @GetMapping("details/{id}")
    public DetailDto getDetails(@PathVariable("id") Long id){
        return userService.getDetails(id);
    }

    @PostMapping("details/{id}")
    public DetailDto setDetails(@PathVariable("id") Long id, @RequestBody DetailDto dto){
        return userService.setDetails(id, dto);
    }

    @GetMapping("plan/{id}")
    public PlanDto getPlane(@PathVariable("id") Long id){
        return userService.getPlan(id);
    }
}
