package ru.jafix.ct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.jafix.ct.service.DocService;
import ru.jafix.ct.service.FileService;

import java.io.IOException;

@RequestMapping("/api")
@RestController
public class TestController {
    @Autowired
    private DocService docService;
    @Autowired
    private FileService fileService;

    @PostMapping("/test")
    public ResponseEntity<?> test() {
        docService.test();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/file")
    public ResponseEntity<?> uploadFile(@RequestBody MultipartFile file) throws IOException {
        fileService.save(file);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/download",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<?> downloadFile(@RequestParam("filename") String filename) throws IOException {
        return ResponseEntity.ok(fileService.receive(filename));
    }
}
