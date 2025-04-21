package ru.jafix.ct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jafix.ct.entity.User;
import ru.jafix.ct.entity.dto.UserDto;
import ru.jafix.ct.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //создать пользователя
    public User createUser(UserDto userDto) {
        if (userDto.getLogin() == null || userDto.getLogin().isEmpty()) {
            throw new IllegalArgumentException("login is required field");
        }

        User userForCreate = User.builder()
                .age(userDto.getAge())
                .login(userDto.getLogin())
                .build();

        return userRepository.save(userForCreate);
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
