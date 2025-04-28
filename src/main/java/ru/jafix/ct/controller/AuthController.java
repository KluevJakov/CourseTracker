package ru.jafix.ct.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.jafix.ct.entity.Responsable;
import ru.jafix.ct.entity.dto.AuthRequest;
import ru.jafix.ct.entity.dto.ErrorDto;
import ru.jafix.ct.entity.dto.SuccessDto;
import ru.jafix.ct.entity.dto.UserDto;
import ru.jafix.ct.service.AuthService;
import ru.jafix.ct.service.JwtService;
import ru.jafix.ct.service.UserService;

import java.util.UUID;

@RequestMapping("/api")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<?> createUser(@RequestBody @Valid AuthRequest authRequest) {
        try {
            return ResponseEntity.ok(SuccessDto.builder().msg(authService.auth(authRequest)).build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ErrorDto.builder()
                    .errorMsg(e.getMessage())
                    .build());
        }
    }

    @GetMapping("/activate/{uuid}")
    public ResponseEntity<?> activate(@PathVariable("uuid") UUID activateCode) {
        try {
            authService.activate(activateCode);
            return ResponseEntity.ok(SuccessDto.builder().msg("Аккаунт активирован").build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ErrorDto.builder()
                    .errorMsg(e.getMessage())
                    .build());
        }
    }
}
