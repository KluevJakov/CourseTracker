package ru.jafix.ct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.jafix.ct.entity.dto.ErrorDto;
import ru.jafix.ct.service.DocService;
import ru.jafix.ct.service.FileService;

@RequestMapping("/api")
@RestController
public class TestController {
    @Autowired
    private DocService docService;
    @Autowired
    private FileService fileService;

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

    @PostMapping("/file")
    public ResponseEntity<?> uploadFile(@RequestBody MultipartFile file) {
        try {
            fileService.save(file);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ErrorDto.builder()
                    .errorMsg(e.getMessage())
                    .build());
        }
    }

    @GetMapping(value = "/download",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<?> downloadFile(@RequestParam("filename") String filename) {
        try {
            return ResponseEntity.ok(fileService.receive(filename));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ErrorDto.builder()
                    .errorMsg(e.getMessage())
                    .build());
        }
    }
}
