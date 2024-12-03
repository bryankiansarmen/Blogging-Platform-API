package com.example.blog.blogging_platform.service;

import com.example.blog.blogging_platform.dto.request.PostDto;
import com.example.blog.blogging_platform.model.Post;
import com.example.blog.blogging_platform.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(PostDto postDto) {
        // create a Post object from the DTO using the Builder pattern
        Post post = Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .category(postDto.getCategory())
                .tags(postDto.getTags())
                .build();

        return postRepository.save(post);
    }

    public Post updatePost(Long id, PostDto postDto) {
        Post existingPost = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found!"));

        existingPost.setTitle(postDto.getTitle());
        existingPost.setContent(postDto.getContent());
        existingPost.setCategory(postDto.getCategory());
        existingPost.setTags(postDto.getTags());

        return postRepository.save(existingPost);
    }
}
