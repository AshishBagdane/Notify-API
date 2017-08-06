package com.ashish.notifyapp.api.exceptions;

import com.ashish.notifyapp.api.models.ErrorMessage;
import com.ashish.notifyapp.api.utilities.GetResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DataUpdateFailedExceptionMapper implements ExceptionMapper<DataUpdateFailedException> {

    @Override
    public Response toResponse(DataUpdateFailedException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 500, "https://ashishbagdane.github.io/");
        return GetResponse.forConflict(errorMessage);
    }
    
}
