package csv.academy.booksandauthors.service;

import csv.academy.booksandauthors.exception.RecordNotFoundException;
import csv.academy.booksandauthors.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {

    Author saveAuthor(Author author);

    Page<Author> findAllAuthors(Pageable pageable);

    Author findAuthorById(Long authorId) throws RecordNotFoundException;

    Author updateAuthor(Long authorId, Author newAuthor) throws RecordNotFoundException;

    void deleteAuthor(Long authorId) throws RecordNotFoundException;

}
