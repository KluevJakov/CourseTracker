package ru.jafix.ct.controller.advisor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.jafix.ct.entity.dto.ErrorDto;

@Slf4j
@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(exception = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handle(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest()
                .body(ErrorDto.builder()
                        .errorMsg(e.getBindingResult().getAllErrors().getFirst().getDefaultMessage())
                        .build());
    }

    @ExceptionHandler(exception = HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleNotReadable(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest()
                .body(ErrorDto.builder()
                        .errorMsg("Тело запроса не должно быть пустым")
                        .build());
    }

    @ExceptionHandler(exception = Exception.class)
    public ResponseEntity<?> commonHandle(Exception e) {
        return ResponseEntity.badRequest()
                .body(ErrorDto.builder()
                        .errorMsg(e.getMessage())
                        .build());
    }
}
