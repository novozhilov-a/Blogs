package com.veeam.interview.novozhilov.blogs.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(Long AuthorId) {
        super("Author with id=" + AuthorId + " not found");
    }
}
