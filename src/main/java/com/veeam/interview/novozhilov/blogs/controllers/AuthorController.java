package com.veeam.interview.novozhilov.blogs.controllers;

import com.veeam.interview.novozhilov.blogs.model.Author;
import com.veeam.interview.novozhilov.blogs.model.AuthorNotFoundException;
import com.veeam.interview.novozhilov.blogs.model.AuthorSmall;
import com.veeam.interview.novozhilov.blogs.model.AuthorsRepo;
import com.veeam.interview.novozhilov.blogs.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {
    private final AuthorsRepo repository;

    @Autowired
    AuthorService authorService;

    AuthorController(AuthorsRepo repository) {
        this.repository = repository;
    }

    @PostMapping("/authors")
    Author createNewAuthor(@RequestBody AuthorSmall smallAuthor){
        return authorService.createNewAuthor(smallAuthor);
    }

    @DeleteMapping("/authors/{id}")
    ResponseEntity<?> deleteAuthor(@PathVariable Long id){
        return authorService.deleteAuthor(id);
    }

    @PutMapping("/authors/{id}")
    Author updateAuthor(@RequestBody AuthorSmall smallAuthor, @PathVariable Long id){
        return authorService.updateAuthor(smallAuthor, id);
    }
    @PostMapping("/authors/findByAuthorName")
    List<Author> searchAuthors(@RequestBody AuthorSmall smallAuthor){
        return authorService.searchAuthors(smallAuthor);
    }

    @GetMapping("/authors")
    Page<Author> getAllAuthors(Pageable pageable) {
        return repository.findAll(pageable);
    }


    @GetMapping("/authors/{id}")
    Author getOneAccount(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new AuthorNotFoundException(id));
    }
}
