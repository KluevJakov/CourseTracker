package ru.jafix.ct.service;

import ru.jafix.ct.entity.dto.AuthRequest;

public interface AuthService {
    String auth(AuthRequest request);
}
