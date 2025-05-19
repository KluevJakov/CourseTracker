package ru.jafix.ct.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.jafix.ct.entity.Course;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private UUID id;
    private UserDto user;
    private Course course;
}
