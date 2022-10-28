package com.academy.week3group3.controllers;

import com.academy.week3group3.exceptions.RecordNotFoundException;
import com.academy.week3group3.model.Author;
import com.academy.week3group3.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;


    // Adding author
    @PostMapping
    public Author saveAuthor(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }

    // Getting all authors
    @GetMapping
    public ResponseEntity<Page<Author>> getAuthors(Pageable pageable) {
        Page<Author> author = authorService.findAllAuthors(pageable);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    // Getting author by id
    @GetMapping("/{authorId}")
    public Author getAuthorById(@PathVariable Long authorId) throws RecordNotFoundException {
        return authorService.findAuthorById(authorId);
    }

    // Update author by id
    @PutMapping("/{authorId}")
    public Author updateAuthor(@PathVariable Long authorId, @RequestBody Author author) throws RecordNotFoundException {
        return authorService.updateAuthor(authorId, author);
    }

    // Delete book by id
    @DeleteMapping("")
    public void deleteBook(@RequestParam Long id) throws RecordNotFoundException {
        authorService.deleteAuthor(id);
    }

}
