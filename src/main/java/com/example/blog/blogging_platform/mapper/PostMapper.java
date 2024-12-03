package com.example.blog.blogging_platform.mapper;

import com.example.blog.blogging_platform.dto.request.PostDto;
import com.example.blog.blogging_platform.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Post toEntity(PostDto postDto);
}
