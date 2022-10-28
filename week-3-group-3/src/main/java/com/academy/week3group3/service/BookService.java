package com.academy.week3group3.service;

import com.academy.week3group3.exceptions.RecordNotFoundException;
import com.academy.week3group3.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    Page<Book> findAllBooks(Pageable pageable);

    Book findBookById(Long id) throws RecordNotFoundException;

    Book saveBook(Book book, Long id);

    Book updateBook(Long id, Book newBook);

    void deleteBook(Long id);

}
