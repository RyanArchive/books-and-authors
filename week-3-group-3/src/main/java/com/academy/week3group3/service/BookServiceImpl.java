package com.academy.week3group3.service;

import com.academy.week3group3.exceptions.RecordNotFoundException;
import com.academy.week3group3.model.Author;
import com.academy.week3group3.model.Book;
import com.academy.week3group3.repository.AuthorRepository;
import com.academy.week3group3.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private AuthorRepository authorRepo;

    @Override
    public Page<Book> findAllBooks(Pageable pageable) {
        return bookRepo.findAll(pageable);
    }


    @Override
    public Book findBookById(Long id) throws RecordNotFoundException {
        Optional<Book> bookOptional = bookRepo.findById(id);
        if (bookOptional.isPresent()) {
            return bookOptional.get();
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public Book saveBook(Book book, Long id) {
        Author author = authorRepo.findById(id).get();
        book.setAuthor(author);
        return bookRepo.save(book);
    }

    @Override
    public Book updateBook(Long id, Book newBook) {
        Book book = bookRepo.findById(id).get();
        book.setTitle(newBook.getTitle());
        book.setDescription(newBook.getDescription());
        book.setAuthor(newBook.getAuthor());
        return bookRepo.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepo.findById(id).get();
        bookRepo.delete(book);
    }

}
