package com.example.blog.blogging_platform.controller;

import com.example.blog.blogging_platform.dto.request.CreatePostDto;
import com.example.blog.blogging_platform.model.Post;
import com.example.blog.blogging_platform.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody CreatePostDto createPostDto) {
        return ResponseEntity.ok(postService.createPost(createPostDto));
    }
}
