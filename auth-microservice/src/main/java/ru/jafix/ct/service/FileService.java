package ru.jafix.ct.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    void save(MultipartFile multipartFile) throws IOException;
    byte[] receive(String filename) throws IOException;
}
