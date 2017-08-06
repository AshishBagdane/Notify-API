/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashish.notifyapp.api.resources;

import com.ashish.notifyapp.api.models.Department;
import com.ashish.notifyapp.api.services.DepartmentService;
import com.ashish.notifyapp.api.services.DepartmentServiceImpl;
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
@Path("/departments")
@Produces(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class DepartmentResource {
    
    private final DepartmentService service;

    public DepartmentResource() {
        this.service = new DepartmentServiceImpl();
    }
    
    @GET
    @Path("/{departmentId}")
    public Response getDepartment(@PathParam("departmentId") long id) {
        Department department = service.getDepartment(id);
        return GetResponse.forFound(department);
    }
    
    @GET
    public Response getAllDepartments() {
        List<Department> departments = service.getAllDepartments();
        return GetResponse.forFound(departments.toArray(new Department[departments.size()]));
    }
    
    @POST
    public Response create(Department department) {
        department = service.create(department);
        return GetResponse.forCreated(department);
    }
    
    @PUT
    @Path("/{departmentId}")
    public Response update(@PathParam("departmentId") long id, Department department) {
        department = service.update(id, department);
        return GetResponse.forOk(department);
    }
    
    @DELETE
    @Path("/{departmentId}")
    public Response delete(@PathParam("departmentId") long id) {
        Department department = service.delete(id);
        return GetResponse.forOk(department);
    }
    
    @DELETE
    public Response delete() {
        service.delete();
        return GetResponse.forNoContent();
    }
    
    @Path("/{departmentId}/users")
    public UserResource getUserResource() {
        return new UserResource();
    }
    
    @Path("/{departmentId}/posts")
    public PostsFromResource getPostsFromResource() {
        return new PostsFromResource();
    }
}
