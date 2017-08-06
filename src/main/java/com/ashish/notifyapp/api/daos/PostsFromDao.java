/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashish.notifyapp.api.daos;

import com.ashish.notifyapp.api.models.Post;
import com.ashish.notifyapp.api.models.PostsFrom;
import java.util.List;

/**
 *
 * @author Ashish
 */
public interface PostsFromDao {
    
    List<Post> getPostsFrom(long departmentId);
    Post getPostFrom(long departmentId, long postId);
    boolean create(PostsFrom postsFrom);
}
