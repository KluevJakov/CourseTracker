package ru.jafix.ct.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
@Builder
public class UserDto {
    private UUID id;
    @NotBlank(message = "Email не должен быть пустым")
    private String email;
    @NotBlank(message = "Пароль не должен быть пустым")
    @Length(min = 8, max = 20, message = "Длина пароля от 8 до 20 символов")
    private String password;
    private int age;
}
