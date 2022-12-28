package csv.academy.booksandauthors.controller;

import csv.academy.booksandauthors.exception.RecordNotFoundException;
import csv.academy.booksandauthors.model.Book;
import csv.academy.booksandauthors.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class BookController {

    @Autowired
    private BookService bookService;


    @PostMapping("/{authorId}/books")
    public Book saveBook(@RequestBody Book book, @PathVariable Long authorId) throws RecordNotFoundException {
        return bookService.saveBook(book, authorId);
    }

    @GetMapping("/{authorId}/books")
    public ResponseEntity<Page<Book>> getBooksByAuthorId(@PathVariable Long authorId, Pageable pageable)
            throws RecordNotFoundException {
        Page<Book> book = bookService.findAllBooks(authorId, pageable);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/{authorId}/books/{bookId}")
    public Book getBookByBookId(@PathVariable Long authorId, @PathVariable Long bookId) throws RecordNotFoundException {
        return bookService.findBookById(authorId, bookId);
    }

    @PutMapping("/{authorId}/books/{bookId}")
    public Book updateBook(@PathVariable Long authorId, @PathVariable Long bookId, @RequestBody Book newBook)
            throws RecordNotFoundException {
        return bookService.updateBook(authorId, bookId, newBook);
    }

    @DeleteMapping("/{authorId}/books")
    public void deleteBook(@PathVariable Long authorId, @RequestParam Long id) throws RecordNotFoundException {
        bookService.deleteBook(authorId, id);
    }

}
