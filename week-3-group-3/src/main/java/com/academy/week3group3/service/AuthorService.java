package com.academy.week3group3.service;

import com.academy.week3group3.exceptions.RecordNotFoundException;
import com.academy.week3group3.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {

    Page<Author> findAllAuthors(Pageable pageable);

    Author findAuthorById(Long authorId) throws RecordNotFoundException;

    Author saveAuthor(Author author);

    Author updateAuthor(Long authorId, Author author) throws RecordNotFoundException;

    void deleteAuthor(Long authorId) throws RecordNotFoundException;

}
