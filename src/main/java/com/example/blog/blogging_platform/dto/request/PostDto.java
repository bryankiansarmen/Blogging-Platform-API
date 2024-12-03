package com.example.blog.blogging_platform.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class PostDto {
    @NotBlank(message = "Title must be not blank")
    private String title;
    @NotBlank(message = "Content must be not blank")
    private String content;
    @NotBlank(message = "Category must be not blank")
    private String category;
    @NotEmpty(message = "Tags must not be empty")
    @Size(min = 1, message = "At least one tag must be provided")
    private List<String> tags;
}
