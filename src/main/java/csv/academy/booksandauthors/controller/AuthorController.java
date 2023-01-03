package csv.academy.booksandauthors.controller;

import csv.academy.booksandauthors.dto.AuthorRequestDTO;
import csv.academy.booksandauthors.dto.AuthorResponseDTO;
import csv.academy.booksandauthors.exception.RecordNotFoundException;
import csv.academy.booksandauthors.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthorController {

    @Autowired
    private AuthorService authorService;


    @PostMapping
    public AuthorResponseDTO saveAuthor(@RequestBody AuthorRequestDTO authorRequestDTO) {
        return authorService.saveAuthor(authorRequestDTO);
    }

    @GetMapping
    public ResponseEntity<Page<AuthorResponseDTO>> getAuthors(Pageable pageable) {
        Page<AuthorResponseDTO> authorResponseDTOPage = authorService.findAllAuthors(pageable);
        return new ResponseEntity<>(authorResponseDTOPage, HttpStatus.OK);
    }

    @GetMapping("/{authorId}")
    public AuthorResponseDTO getAuthorById(@PathVariable Long authorId) throws RecordNotFoundException {
        return authorService.findAuthorById(authorId);
    }

    @PatchMapping("/{authorId}")
    public AuthorResponseDTO updateAuthor(
            @PathVariable Long authorId, @RequestBody AuthorRequestDTO newAuthorRequestDTO
    ) throws RecordNotFoundException {
        return authorService.updateAuthor(authorId, newAuthorRequestDTO);
    }

    @DeleteMapping()
    public void deleteBook(@RequestParam Long id) throws RecordNotFoundException {
        authorService.deleteAuthor(id);
    }

}
