package com.yadev.spring.mapper;

import com.yadev.spring.database.entity.Author;
import com.yadev.spring.dto.AuthorReadDto;
import org.springframework.stereotype.Component;

@Component
public class AuthorReadMapper implements Mapper<Author, AuthorReadDto> {
    @Override
    public AuthorReadDto map(Author object) {
        return new AuthorReadDto(
                object.getId(),
                object.getName()
        );
    }
}
