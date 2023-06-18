package com.yadev.spring.mapper;

import com.yadev.spring.database.entity.Book;
import com.yadev.spring.dto.BookReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookReadMapper implements Mapper<Book, BookReadDto> {

    private final AuthorReadMapper authorReadMapper;

    @Override
    public BookReadDto map(Book book) {

        var authorReadDto = Optional.of(book.getAuthor())
                .map(authorReadMapper::map)
                .orElse(null);

        return new BookReadDto(
                book.getId(),
                book.getTitle(),
                authorReadDto,
                book.getPrice());
    }
}
