package com.ashish.notifyapp.api.daos;

import com.ashish.notifyapp.api.models.Post;
import java.util.List;

public interface PostDao {

    public Post getPost(long departmentId, String username, long postId);
    public List<Post> getAllPosts(long departmentId, String username);
    public Post create(long departmentId, String username, Post post);
    public Post update(long postId, Post post);
    public Post delete(long postId);
    public boolean delete(long departmentId, String username);
}
