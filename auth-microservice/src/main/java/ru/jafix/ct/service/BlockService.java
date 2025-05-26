package ru.jafix.ct.service;

public interface BlockService {
    boolean isBlocked(String ip);
    void authFailure(String ip);
    void authSuccess(String ip);
}
