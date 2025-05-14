package ru.jafix.ct.service;

import io.jsonwebtoken.Claims;

public interface JwtService {
    Claims validateAndParseClaims(String jwt);
}
