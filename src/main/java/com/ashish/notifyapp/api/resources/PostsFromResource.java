/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashish.notifyapp.api.resources;

import com.ashish.notifyapp.api.models.Post;
import com.ashish.notifyapp.api.services.PostsFromService;
import com.ashish.notifyapp.api.services.PostsFromServiceImpl;
import com.ashish.notifyapp.api.utilities.GetResponse;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Ashish
 */
@Produces(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class PostsFromResource {
    
    private final PostsFromService service;

    public PostsFromResource() {
        this.service = new PostsFromServiceImpl();
    }
    
    @GET
    public Response getPostsFrom(@PathParam("departmentId") long departmentId) {
        List<Post> posts = service.getPostsFrom(departmentId);
        return GetResponse.forFound(posts.toArray(new Post[posts.size()]));
    }
    
    @GET
    @Path("/{postId}")
    public Response getPostFrom(@PathParam("departmentId") long departmentId, @PathParam("postId") long postId) {
        Post post = service.getPostFrom(departmentId, postId);
        return GetResponse.forFound(post);
    }
}
