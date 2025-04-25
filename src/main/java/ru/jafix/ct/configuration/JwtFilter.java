package ru.jafix.ct.configuration;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.jafix.ct.entity.Role;
import ru.jafix.ct.entity.dto.JwtAuthentication;
import ru.jafix.ct.service.JwtService;

import java.io.IOException;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final static String BEARER = "Bearer ";
    private final static String AUTH_HEADER = "Authorization";

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwt = parseJwt(request.getHeader(AUTH_HEADER));

        if (jwt == null) {
            filterChain.doFilter(request, response);
            return;
        }

        if (jwtService.validate(jwt)) { //TODO: объединить валидацию + парсинг полезной нагрузки
            Claims claims = jwtService.parseClaims(jwt);
            JwtAuthentication authentication = JwtAuthentication.builder()
                    .login(claims.getSubject())
                    .authority(Role.builder()
                            .name(claims.get("role", String.class))
                            .build())
                    .authenticated(true)
                    .build();
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private String parseJwt(String authHeader) {
        if (authHeader == null) {
            return null;
        }

        if (authHeader.startsWith(BEARER)) {
            return authHeader.substring(BEARER.length());
        }

        return null;
    }
}
