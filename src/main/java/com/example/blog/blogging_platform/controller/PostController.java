package com.example.blog.blogging_platform.controller;

import com.example.blog.blogging_platform.dto.request.PostDto;
import com.example.blog.blogging_platform.model.Post;
import com.example.blog.blogging_platform.service.PostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody @Valid PostDto postDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(postDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<Post> updatePost(@PathVariable @Positive Long id, @RequestBody @Valid PostDto postDto) {
        return ResponseEntity.ok(postService.updatePost(id, postDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePost(@PathVariable @Positive Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Post> getPostById(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPost(@RequestParam(required = false) String term) {
        if (term == null) {
            return ResponseEntity.ok(postService.getAllPost());
        }

        return ResponseEntity.ok(postService.getPostByCustomSearch(term));
    }
}
