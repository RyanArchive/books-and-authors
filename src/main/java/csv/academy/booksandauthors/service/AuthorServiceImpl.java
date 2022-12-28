package csv.academy.booksandauthors.service;

import csv.academy.booksandauthors.dto.AuthorRequestDTO;
import csv.academy.booksandauthors.dto.AuthorResponseDTO;
import csv.academy.booksandauthors.exception.RecordNotFoundException;
import csv.academy.booksandauthors.mapper.AuthorMapper;
import csv.academy.booksandauthors.model.Author;
import csv.academy.booksandauthors.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;


    @Override
    public AuthorResponseDTO saveAuthor(AuthorRequestDTO authorRequestDTO) {
        Author author = authorMapper.requestDtoToModel(authorRequestDTO);
        Author savedAuthor = authorRepository.save(author);
        return authorMapper.modelToResponseDto(savedAuthor);
    }

    @Override
    public Page<AuthorResponseDTO> findAllAuthors(Pageable pageable) {
        List<AuthorResponseDTO> authorResponseDTOList = authorRepository.findAll(pageable)
                .map(authorMapper::modelToResponseDto)
                .toList();
        return new PageImpl<>(authorResponseDTOList, pageable, authorResponseDTOList.size());
    }

    @Override
    public AuthorResponseDTO findAuthorById(Long authorId) throws RecordNotFoundException {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            return authorMapper.modelToResponseDto(author);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public AuthorResponseDTO updateAuthor(Long authorId, AuthorRequestDTO newAuthorRequestDTO)
            throws RecordNotFoundException {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            author.setName(newAuthorRequestDTO.getName());
            Author updatedAuthor = authorRepository.save(author);
            return authorMapper.modelToResponseDto(updatedAuthor);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void deleteAuthor(Long authorId) throws RecordNotFoundException {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (authorOptional.isPresent()) {
            authorRepository.delete(authorOptional.get());
        } else {
            throw new RecordNotFoundException();
        }
    }

}
