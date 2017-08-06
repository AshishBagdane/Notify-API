/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ashish.notifyapp.api.exceptions;

import com.ashish.notifyapp.api.models.ErrorMessage;
import com.ashish.notifyapp.api.utilities.GetResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import org.hibernate.HibernateException;

/**
 *
 * @author Ashish
 */
public class HibernateExceptionMapper implements ExceptionMapper<HibernateException> {

    @Override
    public Response toResponse(HibernateException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 500, "https://ashishbagdane.github.io/");
        return GetResponse.forConflict(errorMessage);
    }
    
}
