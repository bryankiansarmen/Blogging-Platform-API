package com.example.blog.blogging_platform.controller;

import com.example.blog.blogging_platform.dto.request.PostDto;
import com.example.blog.blogging_platform.model.Post;
import com.example.blog.blogging_platform.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostDto postDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(postDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.updatePost(id, postDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(postService.deletePost(id));
    }

    @GetMapping("{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }
}
