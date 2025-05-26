package ru.jafix.ct.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.jafix.ct.entity.dto.AuthRequest;
import ru.jafix.ct.entity.dto.ErrorDto;
import ru.jafix.ct.entity.dto.SuccessDto;
import ru.jafix.ct.service.AuthService;
import ru.jafix.ct.service.BlockService;

import java.util.UUID;

@Slf4j
@SuppressWarnings({"unused"})
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final BlockService blockService;

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody @Valid AuthRequest authRequest, HttpServletRequest httpRequest) {
        String ip = httpRequest.getRemoteAddr();

        if (blockService.isBlocked(ip)) {
            return ResponseEntity.status(429).body(ErrorDto.builder().errorMsg("Превышен лимит запросов. Попробуйте позже.").build());
        }

        try {
            SuccessDto response = SuccessDto.builder().msg(authService.auth(authRequest)).build();
            blockService.authSuccess(ip);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            blockService.authFailure(ip);
            throw e;
        }
    }

    @GetMapping("/activate/{uuid}")
    public ResponseEntity<?> activate(@PathVariable("uuid") UUID activateCode) {
        authService.activate(activateCode);
        return ResponseEntity.ok(SuccessDto.builder().msg("Аккаунт активирован").build());
    }
}
