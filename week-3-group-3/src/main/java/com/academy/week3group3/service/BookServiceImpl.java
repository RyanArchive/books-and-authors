package com.academy.week3group3.service;

import com.academy.week3group3.exceptions.RecordNotFoundException;
import com.academy.week3group3.model.Author;
import com.academy.week3group3.model.Book;
import com.academy.week3group3.repository.AuthorRepository;
import com.academy.week3group3.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private AuthorRepository authorRepo;


    @Override
    public Book saveBook(Book book, Long id) {
        Author author = authorRepo.findById(id).get();
        book.setAuthor(author);
        return bookRepo.save(book);
    }

    @Override
    public Page<Book> findAllBooks(Long authorId, Pageable pageable) {
        List<Book> bookList = bookRepo.findAll(pageable)
                .stream()
                .filter(item -> item.getAuthor().getId().equals(authorId))
                .toList();
        return new PageImpl<>(bookList, pageable, bookList.size());
    }

    @Override
    public Page<Book> findBookById(Long authorId, Long bookId, Pageable pageable) throws RecordNotFoundException {
        Optional<Book> bookOptional = bookRepo.findById(bookId)
                .filter(item -> item.getAuthor().getId().equals(authorId));
        if (bookOptional.isPresent()) {
            List<Book> bookList = bookOptional.stream().toList();
            return new PageImpl<>(bookList, pageable, bookList.size());
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public Book updateBook(Long authorId, Long bookId, Book book) throws RecordNotFoundException {
        Optional<Book> bookOptional = bookRepo.findById(bookId)
                .filter(item -> item.getAuthor().getId().equals(authorId));
        if (bookOptional.isPresent()) {
            List<Book> bookList = bookOptional.stream().toList();
            Book oldBook = bookList.get(0);
            oldBook.setTitle(book.getTitle());
            oldBook.setDescription(book.getDescription());
            return bookRepo.save(oldBook);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void deleteBook(Long authorId, Long bookId) throws RecordNotFoundException {
        Optional<Book> bookOptional = bookRepo.findById(bookId)
                .filter(item -> item.getAuthor().getId().equals(authorId));
        if (bookOptional.isPresent()) {
            List<Book> bookList = bookOptional.stream().toList();
            bookRepo.delete(bookList.get(0));
        } else {
            throw new RecordNotFoundException();
        }
    }

}
