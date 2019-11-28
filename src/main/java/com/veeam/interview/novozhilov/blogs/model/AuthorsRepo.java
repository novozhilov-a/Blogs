package com.veeam.interview.novozhilov.blogs.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorsRepo extends JpaRepository<Author, Long> {
}
