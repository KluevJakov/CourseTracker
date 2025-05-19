package ru.jafix.ct.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("unused")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRequest {
    @NotBlank(message = "Email не должен быть пустым")
    private String email;
    @NotBlank(message = "Пароль не должен быть пустым")
    @Length(min = 8, max = 20, message = "Длина пароля от 8 до 20 символов")
    private String password;
}
