package com.blogapp.com.postservice.controller;

import com.blogapp.com.postservice.dto.PostDTO;
import com.blogapp.com.postservice.dtoMapper.PostMapper;
import com.blogapp.com.postservice.exceptions.DeleteFailedException;
import com.blogapp.com.postservice.exceptions.PostNotFoundException;
import com.blogapp.com.postservice.exceptions.PostNotSavedException;
import com.blogapp.com.postservice.exceptions.PostNotUpdatedException;
import com.blogapp.com.postservice.model.Post;
import com.blogapp.com.postservice.model.Posts;
import com.blogapp.com.postservice.serviceImpl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostServiceImpl postService;
    private PostMapper postMapper;

    @Autowired
    public PostController(PostServiceImpl postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @GetMapping("")
    ResponseEntity<?> getAllPosts(){
        Posts posts = postService.getAll();
        if(posts.size() > 0){
            return new ResponseEntity<>(posts, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new PostNotFoundException("Posts not found").getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Long postId){
        try {
            Post post = postService.getPost(postId);
            if(post != null){
                return new ResponseEntity<>(post, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(new PostNotFoundException("Post by id "+postId+" not found").getMessage(), HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    ResponseEntity<?> addPost(@Valid @RequestBody PostDTO postDTO){
        Post post = postMapper.PostDtoToPost(postDTO);
        Post savedPost = postService.save(post);
        if(savedPost != null){
            PostDTO savedPostDTO = postMapper.PostToPostDto(savedPost);
            return new ResponseEntity<>(savedPostDTO, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new PostNotSavedException("Post saved failed.").getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{postId}")
    ResponseEntity<?> updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable("postId") Long postId){
        Post post = postMapper.PostDtoToPost(postDTO);
        try{
            Post updatedPost = postService.update(post, postId);
            if(updatedPost != null){
                PostDTO updatedPostDTO = postMapper.PostToPostDto(updatedPost);
                return new ResponseEntity<>(updatedPostDTO, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(new PostNotUpdatedException("Post update failed.").getMessage(), HttpStatus.NO_CONTENT);
            }
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{postId}")
    ResponseEntity<?> deletePost(@PathVariable("postId") Long postId){
        Boolean success = postService.delete(postId);
        if(success){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(new DeleteFailedException("Post delete failed.").getMessage(), HttpStatus.CONFLICT);
        }
    }
}
