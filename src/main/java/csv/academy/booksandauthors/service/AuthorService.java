package com.academy.week3group3.service;

import com.academy.week3group3.exception.RecordNotFoundException;
import com.academy.week3group3.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {

    Author saveAuthor(Author author);

    Page<Author> findAllAuthors(Pageable pageable);

    Author findAuthorById(Long authorId) throws RecordNotFoundException;

    Author updateAuthor(Long authorId, Author newAuthor) throws RecordNotFoundException;

    void deleteAuthor(Long authorId) throws RecordNotFoundException;

}
