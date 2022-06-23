package com.blogapp.com.postservice.serviceImpl;

import com.blogapp.com.postservice.model.Post;
import com.blogapp.com.postservice.model.Posts;
import com.blogapp.com.postservice.repository.PostRepository;
import com.blogapp.com.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * Get all the posts from database
     * @return Posts
     */
    @Override
    public Posts getAll() {
        List<Post> postList = postRepository.findAll();
        Posts posts = new Posts();
        posts.setPosts(postList);
        return posts;
    }

    /**
     * Save post
     * @param post
     * @return saved post
     */
    @Override
    public Post save(Post post) {
        return postRepository.save((post));
    }

    /**
     * Update Post post
     * @param post
     * @return updated post
     */
    @Transactional
    @Override
    public Post update(Post post, Long postId) {
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalStateException(
                        "Post with id "+ postId +" doesn't exists."
                ));
        existingPost.setValues(post);
        return postRepository.save(existingPost);
    }

    /**
     * Delete post by id
     * @param postId
     * @return boolean
     */
    @Override
    public Boolean delete(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if(!post.isPresent()){
            return false;
        }else{
            postRepository.delete(post.get());
            return true;
        }
    }
}
