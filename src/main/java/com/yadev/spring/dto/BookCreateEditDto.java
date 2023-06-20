package com.yadev.spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class BookCreateEditDto {

    @NotBlank
    @Size(min = 2)
    private String title;

    private Long authorId;

    @NotNull
    private BigDecimal price;
}
