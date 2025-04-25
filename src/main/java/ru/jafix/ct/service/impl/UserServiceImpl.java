package ru.jafix.ct.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.jafix.ct.entity.Role;
import ru.jafix.ct.entity.User;
import ru.jafix.ct.entity.dto.UserDto;
import ru.jafix.ct.repository.UserRepository;
import ru.jafix.ct.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    //создать пользователя
    @Override
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
    @Override
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
    @Override
    public User findUserById(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Пользователя с таким id не существует");
        }

        return userOptional.get();
    }

    //получить пользователя по login
    @Override
    public User findUserByLogin(String login) {
        Optional<User> userOptional = userRepository.findByLogin(login);

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Пользователя с таким id не существует");
        }

        return userOptional.get();
    }

    //получить список всех пользователей
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    //удалить пользователя по id
    @Override
    public void deleteById(UUID id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException(String.format("Запись по id %s, не найдена", id));
        }

    }
}
