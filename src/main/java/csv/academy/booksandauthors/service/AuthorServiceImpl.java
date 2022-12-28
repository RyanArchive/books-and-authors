package csv.academy.booksandauthors.service;

import csv.academy.booksandauthors.exception.RecordNotFoundException;
import csv.academy.booksandauthors.model.Author;
import csv.academy.booksandauthors.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepo;


    @Override
    public Author saveAuthor(Author author) {
        return authorRepo.save(author);
    }

    @Override
    public Page<Author> findAllAuthors(Pageable pageable) {
        return authorRepo.findAll(pageable);
    }

    @Override
    public Author findAuthorById(Long authorId) throws RecordNotFoundException {
        Optional<Author> authorOptional = authorRepo.findById(authorId);
        if (authorOptional.isPresent()) {
            return authorOptional.get();
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public Author updateAuthor(Long authorId, Author newAuthor) throws RecordNotFoundException {
        Optional<Author> authorOptional = authorRepo.findById(authorId);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            author.setName(newAuthor.getName());
            return authorRepo.save(author);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void deleteAuthor(Long authorId) throws RecordNotFoundException {
        Optional<Author> authorOptional = authorRepo.findById(authorId);
        if (authorOptional.isPresent()) {
            authorRepo.delete(authorOptional.get());
        } else {
            throw new RecordNotFoundException();
        }
    }

}
