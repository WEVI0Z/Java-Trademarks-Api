package ru.wevioz.trademarkapi.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.wevioz.trademarkapi.dto.UserLoginRequestDto;
import ru.wevioz.trademarkapi.dto.UserLoginResponseDto;
import ru.wevioz.trademarkapi.dto.UserRegisterDto;
import ru.wevioz.trademarkapi.dto.UserRegisterRequestDto;
import ru.wevioz.trademarkapi.service.UserService;

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
}
