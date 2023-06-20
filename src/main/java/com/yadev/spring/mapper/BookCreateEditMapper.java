package com.yadev.spring.mapper;

import com.yadev.spring.database.entity.Author;
import com.yadev.spring.database.entity.Book;
import com.yadev.spring.database.repository.AuthorRepository;
import com.yadev.spring.dto.AuthorReadDto;
import com.yadev.spring.dto.BookCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookCreateEditMapper implements Mapper<BookCreateEditDto, Book> {

    private final AuthorRepository authorRepository;

    @Override
    public Book map(BookCreateEditDto object) {
        var book = new Book();
        copy(object, book);
        return book;
    }

    @Override
    public Book map(BookCreateEditDto fromObject, Book toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(BookCreateEditDto object, Book book) {
        book.setTitle(object.getTitle());
        book.setPrice(object.getPrice());
        book.setAuthor(getAuthor(object.getAuthorId()));
    }

    private Author getAuthor(Long authorId) {
        return Optional.ofNullable(authorId)
                .flatMap(authorRepository::findById)
                .orElse(null);
    }
}
