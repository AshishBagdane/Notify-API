/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashish.notifyapp.api.services;

import com.ashish.notifyapp.api.daos.PostsFromDao;
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
public class PostsFromServiceImpl implements PostsFromService {

    private PostsFromDao dao;

    public PostsFromServiceImpl() {
        this.dao = new PostsFromDaoImpl();
    }
    
    @Override
    public List<Post> getPostsFrom(long departmentId) {
        List<Post> posts = dao.getPostsFrom(departmentId);
        if (posts == null || posts.isEmpty()) {
            throw new DataNotFoundException("Resource(s) not found.");
        }
        return posts;
    }

    @Override
    public Post getPostFrom(long departmentId, long postId) {
        Post post = dao.getPostFrom(departmentId, postId);
        if (post == null) {
            throw new DataNotFoundException("Resource not found.");
        }
        return post;
    }
    
}
