package ru.jafix.ct.service;

import ru.jafix.ct.entity.dto.AuthRequest;

import java.util.UUID;

public interface AuthService {
    String auth(AuthRequest request);
    void activate(UUID activateCode);
}
