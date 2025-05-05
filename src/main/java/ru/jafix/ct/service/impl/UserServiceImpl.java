package ru.jafix.ct.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.jafix.ct.configuration.Constants;
import ru.jafix.ct.entity.Role;
import ru.jafix.ct.entity.User;
import ru.jafix.ct.entity.dto.UserDto;
import ru.jafix.ct.repository.RoleRepository;
import ru.jafix.ct.repository.UserRepository;
import ru.jafix.ct.service.MailService;
import ru.jafix.ct.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    //создать пользователя
    @Override
    public UserDto createUser(UserDto userDto) {
        Optional<Role> optRole = roleRepository.findByName(Constants.Roles.STUDENT);

        if (optRole.isEmpty()) {
            throw new IllegalArgumentException("CT-1: обратитесь к администратору");
        }

        User userForCreate = User.builder()
                .age(userDto.getAge())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .enabled(true) //TODO: поменять на false в продуктивной среде
                .activateCode(UUID.randomUUID())
                .role(optRole.get())
                .build();

        userForCreate = userRepository.save(userForCreate);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        String email = userForCreate.getEmail();
        UUID activateCode = userForCreate.getActivateCode();
        executorService.execute(() -> {
            try {
                mailService.send(email, "Активация аккаунта",
                        "Для активации аккаунта перейдите по ссылке: " +
                                "http://localhost:8080/api/activate/" + activateCode);
            } catch (Exception e) {
                log.error("Не получилось отправить код активации на email: {}", email);
            } finally {
                executorService.shutdown();
            }
        });

        return UserDto.builder()
                .id(userForCreate.getId())
                .email(userForCreate.getEmail())
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
                .email(userDto.getEmail())
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

    //получить пользователя по email
    @Override
    public User findUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

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
