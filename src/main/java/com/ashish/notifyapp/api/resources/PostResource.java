/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashish.notifyapp.api.resources;

import com.ashish.notifyapp.api.models.Post;
import com.ashish.notifyapp.api.services.PostService;
import com.ashish.notifyapp.api.services.PostServiceImpl;
import com.ashish.notifyapp.api.utilities.GetResponse;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
public class PostResource {
    
    private final PostService service;

    public PostResource() {
        this.service = new PostServiceImpl();
    }
    
    @GET
    @Path("/{postId}")
    public Response getPost(@PathParam("departmentId") long departmentId, @PathParam("username") String username, @PathParam("postId") long id) {
        Post post = service.getPost(departmentId, username, id);
        return GetResponse.forFound(post);
    }
    
    @GET
    public Response getAllPosts(@PathParam("departmentId") long departmentId, @PathParam("username") String username) {
        List<Post> posts = service.getAllPosts(departmentId, username);
        return GetResponse.forFound(posts.toArray(new Post[posts.size()]));
    }
    
    @POST
    public Response create(@PathParam("departmentId") long departmentId, @PathParam("username") String username, Post post) {
        post = service.create(departmentId, username, post);
        return GetResponse.forCreated(post);
    }
    
    @PUT
    @Path("/{postId}")
    public Response update(@PathParam("postId") long id, Post post) {
        post = service.update(id, post);
        return GetResponse.forOk(post);
    }
    
    @DELETE
    @Path("/{postId}")
    public Response delete(@PathParam("postId") long id) {
        Post post = service.delete(id);
        return GetResponse.forOk(post);
    }
    
    @DELETE
    public Response delete(@PathParam("departmentId") long departmentId, @PathParam("username") String username) {
        service.delete(departmentId, username);
        return GetResponse.forNoContent();
    }
}
