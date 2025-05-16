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
import ru.jafix.ct.service.kafka.DataProducer;

import java.util.UUID;

@RequestMapping("/api/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Responsable> createUser(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @PutMapping
    public ResponseEntity<Responsable> editUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.editUser(userDto));
    }

    @GetMapping
    public ResponseEntity<?> findByIdOrEmail(@RequestParam(value = "userId", required = false) UUID userId,
                                             @RequestParam(value = "email", required = false) String email) {
        if (userId != null && email != null) {
            throw new IllegalArgumentException("Ambigous parameters");
        } else if (userId == null && email == null) {
            return ResponseEntity.ok(userService.findAllUsers());
        } else if (userId != null) {
            return ResponseEntity.ok(userService.findUserById(userId));
        } else {
            return ResponseEntity.ok(userService.findUserByEmail(email));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Responsable> deleteById(@PathVariable("id") UUID id) {
        userService.deleteById(id);
        return ResponseEntity.ok(SuccessDto.builder()
                .msg(String.format("Запись с id = %s, успешно удалена", id))
                .build());
    }
}
