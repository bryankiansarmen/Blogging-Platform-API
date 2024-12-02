package com.example.blog.blogging_platform.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CreatePostDto {
    private String title;
    private String content;
    private String category;
    private List<String> tags;
}
