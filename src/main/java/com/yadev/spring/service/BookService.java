package com.yadev.spring.service;

import com.yadev.spring.database.repository.BookRepository;
import com.yadev.spring.dto.BookCreateEditDto;
import com.yadev.spring.dto.BookReadDto;
import com.yadev.spring.mapper.BookCreateEditMapper;
import com.yadev.spring.mapper.BookReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final BookReadMapper bookReadMapper;
    private final BookCreateEditMapper createEditMapper;

    public List<BookReadDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookReadMapper::map)
                .toList();
    }

    public Optional<BookReadDto> findById(Long id) {
        return bookRepository.findById(id)
                .map(bookReadMapper::map);
    }

    @Transactional //without 'ReadOnly=true' as we create a new object.
    public BookReadDto create(BookCreateEditDto dto) {
        return Optional.of(dto)
                .map(createEditMapper::map)
                .map(bookRepository::save)
                .map(bookReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<BookReadDto> update(Long id, BookCreateEditDto dto) {
        return bookRepository.findById(id)
                .map(book -> {
                    return createEditMapper.map(dto, book);
                })
                .map(bookRepository::saveAndFlush) //no request to the db without 'flush', thus we can get exception on dif.level
                .map(bookReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return bookRepository.findById(id)
                .map(user -> {
                    bookRepository.delete(user);
                    bookRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
