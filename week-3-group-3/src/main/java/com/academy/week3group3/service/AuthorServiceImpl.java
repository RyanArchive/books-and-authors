package com.academy.week3group3.service;

import com.academy.week3group3.exceptions.RecordNotFoundException;
import com.academy.week3group3.model.Author;
import com.academy.week3group3.repository.AuthorRepository;
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
    public Page<Author> findAllAuthors(Pageable pageable) {
        return authorRepo.findAll(pageable);
    }

    @Override
    public Author findAuthorById(Long id) throws RecordNotFoundException {
        Optional<Author> authorOptional = authorRepo.findById(id);
        if (authorOptional.isPresent()) {
            return authorOptional.get();
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public Author saveAuthor(Author author) {
        return authorRepo.save(author);
    }

}