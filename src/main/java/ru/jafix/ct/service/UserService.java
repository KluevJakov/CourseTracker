package ru.jafix.ct.service;

import ru.jafix.ct.entity.User;
import ru.jafix.ct.entity.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDto createUser(UserDto userDto);
    User editUser(UserDto userDto);
    User findUserById(UUID id);
    User findUserByLogin(String login);
    List<User> findAllUsers();
    void deleteById(UUID id);
}
