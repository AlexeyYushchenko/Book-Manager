package com.yadev.spring.mapper;

import com.yadev.spring.database.entity.Author;
import com.yadev.spring.dto.AuthorCreateEditDto;
import org.springframework.stereotype.Component;

@Component
public class AuthorCreateEditMapper implements Mapper<AuthorCreateEditDto, Author> {
    @Override
    public Author map(AuthorCreateEditDto object) {
        var author = new Author();
        copy(object, author);
        return author;
    }

    @Override
    public Author map(AuthorCreateEditDto fromObject, Author toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(AuthorCreateEditDto object, Author author) {
        author.setName(object.getName());
    }
}
