package com.academy.week3group3.service;

import com.academy.week3group3.exception.RecordNotFoundException;
import com.academy.week3group3.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    Book saveBook(Book book, Long authorId) throws RecordNotFoundException;

    Page<Book> findAllBooks(Long authorId, Pageable pageable) throws RecordNotFoundException;

    Page<Book> findBookById(Long authorId, Long bookId, Pageable pageable) throws RecordNotFoundException;

    Book updateBook(Long authorId, Long bookId, Book newBook) throws RecordNotFoundException;

    void deleteBook(Long authorId, Long bookId) throws RecordNotFoundException;

}
