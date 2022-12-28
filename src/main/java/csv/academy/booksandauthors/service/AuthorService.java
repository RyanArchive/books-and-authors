package csv.academy.booksandauthors.service;

import csv.academy.booksandauthors.dto.AuthorRequestDTO;
import csv.academy.booksandauthors.dto.AuthorResponseDTO;
import csv.academy.booksandauthors.exception.RecordNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {

    AuthorResponseDTO saveAuthor(AuthorRequestDTO authorRequestDTO);

    Page<AuthorResponseDTO> findAllAuthors(Pageable pageable);

    AuthorResponseDTO findAuthorById(Long authorId) throws RecordNotFoundException;

    AuthorResponseDTO updateAuthor(Long authorId, AuthorRequestDTO newAuthorRequestDTO) throws RecordNotFoundException;

    void deleteAuthor(Long authorId) throws RecordNotFoundException;

}
