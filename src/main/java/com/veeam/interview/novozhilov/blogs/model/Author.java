package com.veeam.interview.novozhilov.blogs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Author {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private AccountStatus status;

    public enum AccountStatus {
        ACTIVE, DELETED
    }



}
