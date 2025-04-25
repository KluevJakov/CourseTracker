package ru.jafix.ct.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.jafix.ct.entity.User;
import ru.jafix.ct.repository.UserRepository;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userIfPresent = userRepository.findByLogin(username);

        if (userIfPresent.isEmpty()) {
            throw new UsernameNotFoundException(
                    String.format("Пользователь с логином %s не найден", username));
        }

        return userIfPresent.get();
    }
}
