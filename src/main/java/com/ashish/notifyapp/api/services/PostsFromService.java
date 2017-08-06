/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashish.notifyapp.api.services;

import com.ashish.notifyapp.api.models.Post;
import java.util.List;

/**
 *
 * @author Ashish
 */
public interface PostsFromService {
    
    List<Post> getPostsFrom(long departmentId);
    Post getPostFrom(long departmentId, long postId);
}
