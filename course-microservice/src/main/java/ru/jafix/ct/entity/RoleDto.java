package ru.jafix.ct.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto implements GrantedAuthority {
    private UUID id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
