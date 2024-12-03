package com.example.blog.blogging_platform.service;

import com.example.blog.blogging_platform.dto.request.PostDto;
import com.example.blog.blogging_platform.exception.ResourceNotFoundException;
import com.example.blog.blogging_platform.mapper.PostMapper;
import com.example.blog.blogging_platform.model.Post;
import com.example.blog.blogging_platform.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Transactional
    public Post createPost(PostDto postDto) {
        Post post = postMapper.toEntity(postDto);
        return postRepository.save(post);
    }

    @Transactional
    public Post updatePost(Long id, PostDto postDto) {
        Post existingPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));

        existingPost.setTitle(postDto.getTitle());
        existingPost.setContent(postDto.getContent());
        existingPost.setCategory(postDto.getCategory());
        existingPost.setTags(postDto.getTags());

        return postRepository.save(existingPost);
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        postRepository.deleteById(id);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
    }

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    public List<Post> getPostByCustomSearch(String term) {
        return postRepository.findByCustomSearch(term);
    }
}
