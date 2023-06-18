package com.yadev.spring.service;

import com.yadev.spring.database.repository.BookRepository;
import com.yadev.spring.dto.BookReadDto;
import com.yadev.spring.mapper.BookReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final BookReadMapper bookReadMapper;

    public List<BookReadDto> findAll(){
        return bookRepository.findAll().stream()
                .map(bookReadMapper::map)
                .toList();
    }
}
