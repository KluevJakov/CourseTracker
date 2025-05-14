package ru.jafix.ct.service;

import io.jsonwebtoken.Claims;

public interface JwtService {
    String generate(String subject, String authority);
    Claims validateAndParseClaims(String jwt);
}
