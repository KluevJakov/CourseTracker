package ru.jafix.ct.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import ru.jafix.ct.service.BlockService;

import java.time.Duration;

@Slf4j
@RequiredArgsConstructor
@Service
public class BlockServiceImpl implements BlockService {

    private static final Integer MAX_ATTEMPTS = 5;
    private static final Duration EXPIRE_TIME = Duration.ofSeconds(30);

    private final StringRedisTemplate redisTemplate;

    @Override
    public boolean isBlocked(String ip) {
        String strAttempts = redisTemplate.opsForValue().get(ip);

        if (strAttempts == null) {
            return false;
        }

        return Integer.parseInt(strAttempts) >= MAX_ATTEMPTS;
    }

    @Override
    public void authFailure(String ip) {
        Long attepmts = redisTemplate.opsForValue().increment(ip);

        if (attepmts != null && attepmts == 1) {
            redisTemplate.expire(ip, EXPIRE_TIME);
        }
    }

    @Override
    public void authSuccess(String ip) {
        redisTemplate.delete(ip);
    }
}
