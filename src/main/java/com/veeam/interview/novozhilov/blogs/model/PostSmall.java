package com.veeam.interview.novozhilov.blogs.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
public class PostSmall {
    private Long authorId;
    @NotNull @Size(max = 250)
    private String content;
}
