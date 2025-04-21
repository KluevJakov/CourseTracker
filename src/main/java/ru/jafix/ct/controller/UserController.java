package ru.jafix.ct.controller;

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
    public ResponseEntity<Responsable> createUser(@RequestBody UserDto userDto) {
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
    public ResponseEntity<?> findByIdOrLogin(@RequestParam(value = "userId", required = false) UUID userId,
                                      @RequestParam(value = "login", required = false) String login) {
        try {
            if (userId != null && login != null) {
                throw new IllegalArgumentException("Ambigous parameters");
            } else if (userId == null && login == null) {
                return ResponseEntity.ok(userService.findAllUsers());
            } else if (userId != null) {
                return ResponseEntity.ok(userService.findUserById(userId));
            } else {
                return ResponseEntity.ok(userService.findUserByLogin(login));
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
