package csv.academy.booksandauthors.controller;

import csv.academy.booksandauthors.dto.BookRequestDTO;
import csv.academy.booksandauthors.dto.BookResponseDTO;
import csv.academy.booksandauthors.exception.RecordNotFoundException;
import csv.academy.booksandauthors.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    @Autowired
    private BookService bookService;


    @PostMapping("/{authorId}/books")
    public BookResponseDTO saveBook(@RequestBody BookRequestDTO bookRequestDTO, @PathVariable Long authorId)
            throws RecordNotFoundException {
        return bookService.saveBook(bookRequestDTO, authorId);
    }

    @GetMapping("/{authorId}/books")
    public ResponseEntity<Page<BookResponseDTO>> getBooksByAuthorId(@PathVariable Long authorId, Pageable pageable)
            throws RecordNotFoundException {
        Page<BookResponseDTO> bookResponseDTOPage = bookService.findAllBooks(authorId, pageable);
        return new ResponseEntity<>(bookResponseDTOPage, HttpStatus.OK);
    }

    @GetMapping("/{authorId}/books/{bookId}")
    public BookResponseDTO getBookByBookId(@PathVariable Long authorId, @PathVariable Long bookId)
            throws RecordNotFoundException {
        return bookService.findBookById(authorId, bookId);
    }

    @PutMapping("/{authorId}/books/{bookId}")
    public BookResponseDTO updateBook(
            @PathVariable Long authorId, @PathVariable Long bookId, @RequestBody BookRequestDTO newBookRequestDTO
    ) throws RecordNotFoundException {
        return bookService.updateBook(authorId, bookId, newBookRequestDTO);
    }

    @DeleteMapping("/{authorId}/books")
    public void deleteBook(@PathVariable Long authorId, @RequestParam Long id) throws RecordNotFoundException {
        bookService.deleteBook(authorId, id);
    }

}
