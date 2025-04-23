package ru.jafix.ct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.jafix.ct.entity.Role;
import ru.jafix.ct.entity.User;
import ru.jafix.ct.entity.dto.UserDto;
import ru.jafix.ct.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    //создать пользователя
    public UserDto createUser(UserDto userDto) {
        if (userDto.getLogin() == null || userDto.getLogin().isEmpty()) {
            throw new IllegalArgumentException("login is required field");
        }

        if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            throw new IllegalArgumentException("password is required field");
        }

        User userForCreate = User.builder()
                .age(userDto.getAge())
                .login(userDto.getLogin())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(Role.builder()
                        .id(UUID.fromString("8b60d3a0-e5d9-41b1-902d-ae529cfe8fac"))
                        .build())
                .build();

        userForCreate = userRepository.save(userForCreate);
        return UserDto.builder()
                .id(userForCreate.getId())
                .login(userForCreate.getLogin())
                .age(userForCreate.getAge())
                .build();
    }

    //изменить пользователя
    public User editUser(UserDto userDto) {
        if (userDto.getId() == null) {
            throw new IllegalArgumentException("id пользователя при изменении обязателен");
        }

        User userForCreate = User.builder()
                .id(userDto.getId())
                .age(userDto.getAge())
                .login(userDto.getLogin())
                .build();

        return userRepository.save(userForCreate);
    }

    //получить пользователя по id
    public User findUserById(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Пользователя с таким id не существует");
        }

        return userOptional.get();
    }

    //получить пользователя по login
    public User findUserByLogin(String login) {
        Optional<User> userOptional = userRepository.findByLogin(login);

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Пользователя с таким id не существует");
        }

        return userOptional.get();
    }

    //получить список всех пользователей
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    //удалить пользователя по id
    public void deleteById(UUID id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException(String.format("Запись по id %s, не найдена", id));
        }

    }
}
