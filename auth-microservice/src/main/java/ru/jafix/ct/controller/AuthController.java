package ru.jafix.ct.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.jafix.ct.entity.dto.AuthRequest;
import ru.jafix.ct.entity.dto.SuccessDto;
import ru.jafix.ct.service.AuthService;

import java.util.UUID;

@SuppressWarnings({"unused"})
@RequestMapping("/api")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<?> createUser(@RequestBody @Valid AuthRequest authRequest) {
        return ResponseEntity.ok(SuccessDto.builder().msg(authService.auth(authRequest)).build());
    }

    @GetMapping("/activate/{uuid}")
    public ResponseEntity<?> activate(@PathVariable("uuid") UUID activateCode) {
        authService.activate(activateCode);
        return ResponseEntity.ok(SuccessDto.builder().msg("Аккаунт активирован").build());
    }
}
