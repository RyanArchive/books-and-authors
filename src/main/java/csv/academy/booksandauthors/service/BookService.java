package csv.academy.booksandauthors.service;

import csv.academy.booksandauthors.dto.BookRequestDTO;
import csv.academy.booksandauthors.dto.BookResponseDTO;
import csv.academy.booksandauthors.exception.RecordNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    BookResponseDTO saveBook(BookRequestDTO bookRequestDTO, Long authorId) throws RecordNotFoundException;

    Page<BookResponseDTO> findAllBooks(Long authorId, Pageable pageable) throws RecordNotFoundException;

    BookResponseDTO findBookById(Long authorId, Long bookId) throws RecordNotFoundException;

    BookResponseDTO updateBook(Long authorId, Long bookId, BookRequestDTO newBookRequestDTO)
            throws RecordNotFoundException;

    void deleteBook(Long authorId, Long bookId) throws RecordNotFoundException;

}
