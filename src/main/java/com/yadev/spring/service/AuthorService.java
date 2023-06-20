package com.yadev.spring.service;

import com.yadev.spring.database.repository.AuthorRepository;
import com.yadev.spring.dto.AuthorCreateEditDto;
import com.yadev.spring.dto.AuthorReadDto;
import com.yadev.spring.mapper.AuthorCreateEditMapper;
import com.yadev.spring.mapper.AuthorReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorReadMapper authorReadMapper;
    private final AuthorCreateEditMapper createEditMapper;

    public List<AuthorReadDto> findAll() {
        return authorRepository.findAll().stream()
                .map(authorReadMapper::map)
                .toList();
    }

    public Optional<AuthorReadDto> findById(Long id){
        return authorRepository.findById(id)
                .map(authorReadMapper::map);
    }

    @Transactional //without 'ReadOnly=true' as we create a new object.
    public AuthorReadDto create(AuthorCreateEditDto authorDto) {
        return Optional.of(authorDto)
                .map(createEditMapper::map)
                .map(authorRepository::save)
                .map(authorReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<AuthorReadDto> update(Long id, AuthorCreateEditDto dto) {
        return authorRepository.findById(id)
                .map(author -> { return createEditMapper.map(dto, author);})
                .map(authorRepository::saveAndFlush) //no request to the db without 'flush', thus we can get exception on dif.level
                .map(authorReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return authorRepository.findById(id)
                .map(user -> {
                    authorRepository.delete(user);
                    authorRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
