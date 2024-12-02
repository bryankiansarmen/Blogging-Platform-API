package com.example.blog.blogging_platform.service;

import com.example.blog.blogging_platform.dto.request.CreatePostDto;
import com.example.blog.blogging_platform.model.Post;
import com.example.blog.blogging_platform.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(CreatePostDto createPostDto) {
        // create a Post object from the DTO using the Builder pattern
        Post post = Post.builder()
                .title(createPostDto.getTitle())
                .content(createPostDto.getContent())
                .category(createPostDto.getCategory())
                .tags(createPostDto.getTags())
                .build();

        return postRepository.save(post);
    }
}
