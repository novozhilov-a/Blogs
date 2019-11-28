package com.veeam.interview.novozhilov.blogs.service;

import com.veeam.interview.novozhilov.blogs.model.Author;
import com.veeam.interview.novozhilov.blogs.model.AuthorNotFoundException;
import com.veeam.interview.novozhilov.blogs.model.AuthorSmall;
import com.veeam.interview.novozhilov.blogs.model.AuthorsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorsRepo authorsRepo;

    public ResponseEntity<?> deleteAuthor(Long id){
        Author author = authorsRepo.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
        author.setStatus(Author.AccountStatus.DELETED);
        authorsRepo.save(author);
        return ResponseEntity.ok().build();
    }

    public Author createNewAuthor(AuthorSmall smallAccount){
        Author account = new Author(null,
                smallAccount.getName(),
                Author.AccountStatus.ACTIVE
                );
        return authorsRepo.save(account);
    }

    public Author updateAuthor(AuthorSmall smallAuthor, Long id) {
        Author author = authorsRepo.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
        author.setName(smallAuthor.getName());
        return authorsRepo.save(author);
    }

    public List<Author> searchAuthors(AuthorSmall smallAuthor) {
        Author searchExample = new Author();
        searchExample.setName(smallAuthor.getName());
        return authorsRepo.findAll(Example.of(searchExample));
    }
}
