/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashish.notifyapp.api.utilities;

import javax.ws.rs.core.Response;

/**
 *
 * @author Ashish
 */
public class GetResponse {
    
    public static Response forFound(Object entity) {
        return Response.status(Response.Status.FOUND)
                .entity(entity)
                .build();
    }
    
    public static Response forNotFound(Object entity) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(entity)
                .build();
    }
    
    public static Response forCreated() {
        return Response.status(Response.Status.CREATED)
                .build();
    }
    
    public static Response forCreated(Object entity) {
        return Response.status(Response.Status.CREATED)
                .entity(entity)
                .build();
    }
    
    public static Response forOk(Object entity) {
        return Response.ok(entity).build();
    }
    
    public static Response forNoContent() {
        return Response.noContent().build();
    }
    
    public static Response forInternalServerError(Object entity) {
        return Response.serverError()
                .entity(entity)
                .build();
    }
    
    public static Response forConflict(Object entity) {
        return Response.status(Response.Status.CONFLICT)
                .entity(entity)
                .build();
    }
    
}
