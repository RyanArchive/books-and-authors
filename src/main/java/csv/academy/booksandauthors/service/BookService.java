package csv.academy.booksandauthors.service;

import csv.academy.booksandauthors.exception.RecordNotFoundException;
import csv.academy.booksandauthors.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    Book saveBook(Book book, Long authorId) throws RecordNotFoundException;

    Page<Book> findAllBooks(Long authorId, Pageable pageable) throws RecordNotFoundException;

    Book findBookById(Long authorId, Long bookId) throws RecordNotFoundException;

    Book updateBook(Long authorId, Long bookId, Book newBook) throws RecordNotFoundException;

    void deleteBook(Long authorId, Long bookId) throws RecordNotFoundException;

}
