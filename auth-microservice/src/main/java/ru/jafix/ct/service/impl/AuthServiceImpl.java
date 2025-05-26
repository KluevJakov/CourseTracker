package ru.jafix.ct.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.jafix.ct.entity.User;
import ru.jafix.ct.entity.dto.AuthRequest;
import ru.jafix.ct.repository.UserRepository;
import ru.jafix.ct.service.AuthService;
import ru.jafix.ct.service.JwtService;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public String auth(AuthRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

        if (!userDetails.isEnabled()) {
            throw new AuthenticationServiceException("Аккаунт не активирован");
        }

        if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            throw new AuthenticationServiceException("Неверный пароль");
        }

        Optional<? extends GrantedAuthority> role = userDetails.getAuthorities().stream().findFirst();

        if (role.isEmpty()) {
            throw new IllegalArgumentException("---");
        }

        return jwtService.generate(userDetails.getUsername(), role.get().getAuthority());
    }

    @Override
    public void activate(UUID activateCode) {
        User userByCode = userRepository.findByActivateCode(activateCode)
                .orElseThrow(() -> new IllegalArgumentException("Неверный код активации"));

        userByCode.setActivateCode(null);
        userByCode.setEnabled(true);

        userRepository.save(userByCode);
    }
}
