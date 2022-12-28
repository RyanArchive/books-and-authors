package csv.academy.booksandauthors.service;

import csv.academy.booksandauthors.exception.RecordNotFoundException;
import csv.academy.booksandauthors.model.Author;
import csv.academy.booksandauthors.model.Book;
import csv.academy.booksandauthors.repository.AuthorRepository;
import csv.academy.booksandauthors.repository.BookRepository;
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
    public Book saveBook(Book book, Long authorId) throws RecordNotFoundException {
        Optional<Author> authorOptional = authorRepo.findById(authorId);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.stream().findFirst().get();
            book.setAuthor(author);
            return bookRepo.save(book);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public Page<Book> findAllBooks(Long authorId, Pageable pageable) throws RecordNotFoundException {
        Optional<Author> authorOptional = authorRepo.findById(authorId);
        if (authorOptional.isPresent()) {
            List<Book> bookList = bookRepo.findAll(pageable).stream().filter(item -> item.getAuthor().getId().equals(authorId)).toList();
            return new PageImpl<>(bookList, pageable, bookList.size());
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public Page<Book> findBookById(Long authorId, Long bookId, Pageable pageable) throws RecordNotFoundException {
        Optional<Book> bookOptional = bookRepo.findById(bookId).filter(item -> item.getAuthor().getId().equals(authorId));
        if (bookOptional.isPresent()) {
            List<Book> bookList = bookOptional.stream().toList();
            return new PageImpl<>(bookList, pageable, bookList.size());
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public Book updateBook(Long authorId, Long bookId, Book newBook) throws RecordNotFoundException {
        Optional<Book> bookOptional = bookRepo.findById(bookId).filter(item -> item.getAuthor().getId().equals(authorId));
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setTitle(newBook.getTitle());
            book.setDescription(newBook.getDescription());
            return bookRepo.save(book);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void deleteBook(Long authorId, Long bookId) throws RecordNotFoundException {
        Optional<Book> bookOptional = bookRepo.findById(bookId).filter(item -> item.getAuthor().getId().equals(authorId));
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            bookRepo.delete(book);
        } else {
            throw new RecordNotFoundException();
        }
    }

}
