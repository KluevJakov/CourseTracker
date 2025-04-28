package ru.jafix.ct.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.jafix.ct.entity.Responsable;
import ru.jafix.ct.entity.dto.ErrorDto;
import ru.jafix.ct.entity.dto.SuccessDto;
import ru.jafix.ct.entity.dto.UserDto;
import ru.jafix.ct.service.UserService;

import java.util.UUID;

@RequestMapping("/api")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<Responsable> createUser(@RequestBody @Valid UserDto userDto) {
        try {
            return ResponseEntity.ok(userService.createUser(userDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ErrorDto.builder()
                    .errorMsg(e.getMessage())
                    .build());
        }
    }

    @PutMapping("/users")
    public ResponseEntity<Responsable> editUser(@RequestBody UserDto userDto) {
        try {
            return ResponseEntity.ok(userService.editUser(userDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ErrorDto.builder()
                    .errorMsg(e.getMessage())
                    .build());
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> findByIdOrEmail(@RequestParam(value = "userId", required = false) UUID userId,
                                      @RequestParam(value = "email", required = false) String email) {
        try {
            if (userId != null && email != null) {
                throw new IllegalArgumentException("Ambigous parameters");
            } else if (userId == null && email == null) {
                return ResponseEntity.ok(userService.findAllUsers());
            } else if (userId != null) {
                return ResponseEntity.ok(userService.findUserById(userId));
            } else {
                return ResponseEntity.ok(userService.findUserByEmail(email));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ErrorDto.builder()
                    .errorMsg(e.getMessage())
                    .build());
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Responsable> deleteById(@PathVariable("id") UUID id) {
        try {
            userService.deleteById(id);
            return ResponseEntity.ok(SuccessDto.builder()
                    .msg(String.format("Запись с id = %s, успешно удалена", id))
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ErrorDto.builder()
                    .errorMsg(e.getMessage())
                    .build());
        }
    }
}
