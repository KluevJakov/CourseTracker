package ru.jafix.ct.service;

import io.jsonwebtoken.Claims;

public interface JwtService {
    String generate(String subject, String authority);
    boolean validate(String jwt);
    Claims parseClaims(String jwt);
}
