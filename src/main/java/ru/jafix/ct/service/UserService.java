package ru.jafix.ct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jafix.ct.entity.User;
import ru.jafix.ct.entity.dto.UserDto;
import ru.jafix.ct.repository.UserRepository;

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
        User userForCreate = User.builder()
                .id(userDto.getId())
                .age(userDto.getAge())
                .login(userDto.getLogin())
                .build();

        return userRepository.save(userForCreate);
    }

    //получить пользователя по id

    //получить пользователя по login

    //получить список всех пользователей

    //удалить пользователя по id
}
