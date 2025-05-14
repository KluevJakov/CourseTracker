package ru.jafix.ct.service;

public interface MailService {
    void send(String addressee, String subject, String content);
}
