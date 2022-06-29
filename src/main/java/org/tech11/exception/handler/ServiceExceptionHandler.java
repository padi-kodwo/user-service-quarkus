package org.tech11.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tech11.domain.ApiResponse;
import org.tech11.domain.BaseError;
import org.tech11.exception.ServiceException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ServiceExceptionHandler implements ExceptionMapper<ServiceException> {
    private static final Logger logger = LoggerFactory.getLogger(ServiceExceptionHandler.class);


    @Override
    public Response toResponse(ServiceException ex) {

        BaseError baseError = new BaseError();
        baseError.setUrl("n/a");
        baseError.setErrorCode(ex.getCode());
        baseError.setErrorMessage(ex.getMessage());

        ApiResponse<Object> errorResponse = new ApiResponse<>();
        errorResponse.setCode(100);
        errorResponse.setMessage("failed");
        errorResponse.setError(baseError);

        logger.error("[ HTTP ERROR:ServiceExceptionHandler {}", ex.getMessage());

        return Response.status(Response.Status.OK).entity(errorResponse).build();
    }
}