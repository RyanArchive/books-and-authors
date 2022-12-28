package csv.academy.booksandauthors.controller;

import csv.academy.booksandauthors.exception.RecordNotFoundException;
import csv.academy.booksandauthors.model.Author;
import csv.academy.booksandauthors.service.AuthorService;
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


    @PostMapping
    public Author saveAuthor(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }

    @GetMapping
    public ResponseEntity<Page<Author>> getAuthors(Pageable pageable) {
        Page<Author> author = authorService.findAllAuthors(pageable);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @GetMapping("/{authorId}")
    public Author getAuthorById(@PathVariable Long authorId) throws RecordNotFoundException {
        return authorService.findAuthorById(authorId);
    }

    @PutMapping("/{authorId}")
    public Author updateAuthor(@PathVariable Long authorId, @RequestBody Author newAuthor)
            throws RecordNotFoundException {
        return authorService.updateAuthor(authorId, newAuthor);
    }

    @DeleteMapping()
    public void deleteBook(@RequestParam Long id) throws RecordNotFoundException {
        authorService.deleteAuthor(id);
    }

}
