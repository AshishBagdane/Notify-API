/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashish.notifyapp.api.services;

import com.ashish.notifyapp.api.daos.PostDao;
import com.ashish.notifyapp.api.daos.PostDaoImpl;
import com.ashish.notifyapp.api.daos.PostsFromDaoImpl;
import com.ashish.notifyapp.api.exceptions.DataNotFoundException;
import com.ashish.notifyapp.api.exceptions.DataUpdateFailedException;
import com.ashish.notifyapp.api.models.Post;
import com.ashish.notifyapp.api.models.PostsFrom;
import java.util.List;

/**
 *
 * @author Ashish
 */
public class PostServiceImpl implements PostService {
    
    private final PostDao dao;

    public PostServiceImpl() {
        this.dao = new PostDaoImpl();
    }

    @Override
    public Post getPost(long departmentId, String username, long id) {
        Post post = dao.getPost(departmentId, username, id);
        if (post == null) {
            throw new DataNotFoundException("Resource not found.");
        }
        return post;
    }

    @Override
    public List<Post> getAllPosts(long departmentId, String username) {
        List<Post> posts = dao.getAllPosts(departmentId, username);
        if (posts == null || posts.isEmpty()) {
            throw new DataNotFoundException("Resource(s) not found.");
        }
        return posts;
    }

    @Override
    public Post create(long departmentId, String username, Post post) {
        post = dao.create(departmentId, username, post);
        if (post == null) {
            throw new DataUpdateFailedException("Resource creation failed.");
        } else {
            PostsFrom postsFrom = new PostsFrom(post.getId(), departmentId, username);
            boolean status = new PostsFromDaoImpl().create(postsFrom);
            if (!status) {
                dao.delete(post.getId());
                post = null;
                throw new DataUpdateFailedException("Sub-resource creation failed.");
            }
        }
        return post;
    }

    @Override
    public Post update(long id, Post post) {
        post = dao.update(id, post);
        if (post == null) {
            throw new DataNotFoundException("Resource not found.");
        }
        return post;
    }

    @Override
    public Post delete(long id) {
        Post post = dao.delete(id);
        if (post == null) {
            throw new DataNotFoundException("Resource not found.");
        }
        return post;
    }

    @Override
    public boolean delete(long departmentId, String username) {
        boolean status = dao.delete(departmentId, username);
        if (!status) {
            throw new DataNotFoundException("Resource(s) not found.");
        }
        return status;
    }
    
}
