package ru.jafix.ct.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.jafix.ct.configuration.FeignConfig;
import ru.jafix.ct.entity.dto.UserDto;

import java.util.UUID;

@FeignClient(name = "auth-service", url = "${auth-service.url}", configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("/api/users")
    UserDto getUserById(@RequestParam("userId") UUID userId);

}
