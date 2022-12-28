package csv.academy.booksandauthors.service;

import csv.academy.booksandauthors.dto.BookRequestDTO;
import csv.academy.booksandauthors.dto.BookResponseDTO;
import csv.academy.booksandauthors.exception.RecordNotFoundException;
import csv.academy.booksandauthors.mapper.BookMapper;
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
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookMapper bookMapper;


    @Override
    public BookResponseDTO saveBook(BookRequestDTO bookRequestDTO, Long authorId) throws RecordNotFoundException {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            Book book = bookMapper.requestDtoToModel(bookRequestDTO);
            book.setAuthor(author);
            Book savedBook = bookRepository.save(book);
            return bookMapper.modelToResponseDto(savedBook);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public Page<BookResponseDTO> findAllBooks(Long authorId, Pageable pageable) throws RecordNotFoundException {
        List<BookResponseDTO> bookList = bookRepository.findBookByAuthorId(authorId)
                .stream()
                .map(bookMapper::modelToResponseDto)
                .toList();
        if (!bookList.isEmpty()) {
            return new PageImpl<>(bookList, pageable, bookList.size());
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public BookResponseDTO findBookById(Long authorId, Long bookId) throws RecordNotFoundException {
        Optional<Book> bookOptional = bookRepository.findBookByAuthorIdAndId(authorId, bookId);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            return bookMapper.modelToResponseDto(book);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public BookResponseDTO updateBook(Long authorId, Long bookId, BookRequestDTO newBookRequestDTO)
            throws RecordNotFoundException {
        Optional<Book> bookOptional = bookRepository.findBookByAuthorIdAndId(authorId, bookId);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setTitle(newBookRequestDTO.getTitle());
            book.setDescription(newBookRequestDTO.getDescription());
            Book updatedBook = bookRepository.save(book);
            return bookMapper.modelToResponseDto(updatedBook);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void deleteBook(Long authorId, Long bookId) throws RecordNotFoundException {
        Optional<Book> bookOptional = bookRepository.findBookByAuthorIdAndId(authorId, bookId);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            bookRepository.delete(book);
        } else {
            throw new RecordNotFoundException();
        }
    }

}
