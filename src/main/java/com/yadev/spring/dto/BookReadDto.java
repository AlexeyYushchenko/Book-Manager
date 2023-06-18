package com.yadev.spring.dto;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class BookReadDto {
    Long id;
    String title;
    AuthorReadDto author;
    BigDecimal price;
}
