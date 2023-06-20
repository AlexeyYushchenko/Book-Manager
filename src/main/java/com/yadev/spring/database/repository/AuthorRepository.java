package com.yadev.spring.database.repository;

import com.yadev.spring.database.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
