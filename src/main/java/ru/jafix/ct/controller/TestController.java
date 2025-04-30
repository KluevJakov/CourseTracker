package ru.jafix.ct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.jafix.ct.entity.dto.ErrorDto;
import ru.jafix.ct.service.DocService;

@RequestMapping("/api")
@RestController
public class TestController {

    @Autowired
    private DocService docService;

    @PostMapping("/test")
    public ResponseEntity<?> test() {
        try {
            docService.test();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ErrorDto.builder()
                    .errorMsg(e.getMessage())
                    .build());
        }
    }
}
