package com.blogapp.com.postservice.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;

@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Posts {
    private Collection<Post> posts;

    public Collection<Post> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }

    public int size(){
        return this.posts.size();
    }
}
