package ru.jafix.ct.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import ru.jafix.ct.configuration.Constants;
import ru.jafix.ct.constants.TestConstants;
import ru.jafix.ct.entity.User;
import ru.jafix.ct.entity.dto.UserDto;
import ru.jafix.ct.repository.RoleRepository;
import ru.jafix.ct.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockitoSpyBean
    private UserRepository userRepository;

    @MockitoSpyBean
    private RoleRepository roleRepository;
/*
    @BeforeEach
    public void beforeEach() {
        userRepository.deleteAll();
    }

    @Test
    public void createUserSuccess() {
        UserDto param = UserDto.builder()
                .email(TestConstants.email)
                .password(TestConstants.password)
                .age(TestConstants.age)
                .build();

        UserDto actual = userService.createUser(param);

        assertEquals(TestConstants.email, actual.getEmail());
        assertEquals(TestConstants.age, actual.getAge());
        assertNull(actual.getPassword());
        assertInstanceOf(UUID.class, actual.getId());
    }

    @Test
    public void createUserExistedEmailFailed() {
        UserDto param = UserDto.builder()
                .email(TestConstants.email)
                .password(TestConstants.password)
                .age(TestConstants.age)
                .build();

        when(userRepository.findByEmail(any()))
                .thenReturn(Optional.of(User.builder()
                                .id(UUID.randomUUID())
                                .email(TestConstants.email)
                        .build()));

        Executable e = () -> userService.createUser(param);

        assertThrows(IllegalArgumentException.class, e);
    }

    @Test
    public void createUserRoleNotFoundFailed() {
        UserDto param = UserDto.builder()
                .email(TestConstants.email)
                .password(TestConstants.password)
                .age(TestConstants.age)
                .build();

        when(roleRepository.findByName(eq(Constants.Roles.STUDENT)))
                .thenReturn(Optional.empty());

        Executable e = () -> userService.createUser(param);

        assertThrows(IllegalArgumentException.class, e);
    }*/
}
