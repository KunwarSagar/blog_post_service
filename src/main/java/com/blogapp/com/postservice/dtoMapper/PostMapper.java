package com.blogapp.com.postservice.dtoMapper;

import com.blogapp.com.postservice.dto.PostDTO;
import com.blogapp.com.postservice.model.Post;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    PostDTO PostToPostDto(Post post);

    @InheritInverseConfiguration
    Post PostDtoToPost(PostDTO postDTO);
}
