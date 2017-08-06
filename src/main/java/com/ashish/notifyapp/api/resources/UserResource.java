/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashish.notifyapp.api.resources;

import com.ashish.notifyapp.api.models.User;
import com.ashish.notifyapp.api.services.UserService;
import com.ashish.notifyapp.api.services.UserServiceImpl;
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
public class UserResource {
    
    private final UserService service;

    public UserResource() {
        this.service = new UserServiceImpl();
    }
    
    @GET
    @Path("/{username}")
    public Response getUser(@PathParam("departmentId") long departmentId, @PathParam("username") String username) {
        User user = service.getUser(departmentId, username);
        return GetResponse.forFound(user);
    }
    
    @GET
    public Response getAllUsers(@PathParam("departmentId") long departmentId) {
        List<User> users = service.getAllUsers(departmentId);
        return GetResponse.forFound(users.toArray(new User[users.size()]));
    }
    
    @POST
    public Response create(@PathParam("departmentId") long departmentId, User user) {
        user = service.create(departmentId, user);
        return GetResponse.forCreated(user);
    }
    
    @PUT
    @Path("/{username}")
    public Response update(@PathParam("departmentId") long departmentId, @PathParam("username") String username, User user) {
        user = service.update(departmentId, username, user);
        return GetResponse.forOk(user);
    }
    
    @DELETE
    @Path("/{username}")
    public Response delete(@PathParam("departmentId") long departmentId, @PathParam("username") String username) {
        User user = service.delete(departmentId, username);
        return GetResponse.forOk(user);
    }
    
    @DELETE
    public Response delete(@PathParam("departmentId") long departmentId) {
        service.delete(departmentId);
        return GetResponse.forNoContent();
    }
    
    @Path("/{username}/posts")
    public PostResource getPostResource() {
        return new PostResource();
    }
}
