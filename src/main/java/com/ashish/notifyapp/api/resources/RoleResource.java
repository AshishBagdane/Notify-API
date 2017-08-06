/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashish.notifyapp.api.resources;

import com.ashish.notifyapp.api.models.Role;
import com.ashish.notifyapp.api.services.RoleService;
import com.ashish.notifyapp.api.services.RoleServiceImpl;
import com.ashish.notifyapp.api.utilities.GetResponse;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Ashish
 */
@Path("/roles")
@Produces(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class RoleResource {
    
    private final RoleService service;

    public RoleResource() {
        this.service = new RoleServiceImpl();
    }
    
    @GET
    @Path("/{roleId}")
    public Response getRole(@PathParam("roleId") long id) {
        Role role = service.getRole(id);
        return GetResponse.forFound(role);
    }
    
    @GET
    public Response getAllRoles() {
        List<Role> roles = service.getAllRoles();
        return GetResponse.forFound(roles.toArray(new Role[roles.size()]));
    }
    
    @POST
    public Response create(Role role) {
        role = service.create(role);
        return GetResponse.forCreated(role);
    }
    
    @DELETE
    @Path("/{roleId}")
    public Response delete(@PathParam("roleId") long id) {
        Role role = service.delete(id);
        return GetResponse.forOk(role);
    }
    
    @DELETE
    public Response delete() {
        service.delete();
        return GetResponse.forNoContent();
    }
}
