package com.yadev.spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class AuthorCreateEditDto {

    @NotBlank
    @Size(min = 2)
    String name;
}
