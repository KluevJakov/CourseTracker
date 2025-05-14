package ru.jafix.ct.entity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.jafix.ct.entity.Responsable;

@Getter
@Setter
@Builder
public class ErrorDto implements Responsable {
    private String errorMsg;
}
