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
            Author author = authorOptional.get();
            book.setAuthor(author);
            return bookRepo.save(book);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public Page<Book> findAllBooks(Long authorId, Pageable pageable) throws RecordNotFoundException {
        List<Book> bookList = bookRepo.findBookByAuthorId(authorId);
        if (!bookList.isEmpty()) {
            return new PageImpl<>(bookList, pageable, bookList.size());
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public Book findBookById(Long authorId, Long bookId) throws RecordNotFoundException {
        Optional<Book> bookOptional = bookRepo.findBookByAuthorIdAndId(authorId, bookId);
        if (bookOptional.isPresent()) {
            return bookOptional.get();
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public Book updateBook(Long authorId, Long bookId, Book newBook) throws RecordNotFoundException {
        Optional<Book> bookOptional = bookRepo.findBookByAuthorIdAndId(authorId, bookId);
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
        Optional<Book> bookOptional = bookRepo.findBookByAuthorIdAndId(authorId, bookId);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            bookRepo.delete(book);
        } else {
            throw new RecordNotFoundException();
        }
    }

}
