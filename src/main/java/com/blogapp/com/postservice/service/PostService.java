package com.blogapp.com.postservice.service;

import com.blogapp.com.postservice.model.Post;
import com.blogapp.com.postservice.model.Posts;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
    Posts getAll();
    Post getPost(Long postId);
    Post save(Post post);
    Post update(Post post, Long postId);
    Boolean delete(Long postId);
}
