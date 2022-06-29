package org.tech11.exception.handler;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tech11.domain.ApiResponse;
import org.tech11.domain.BaseError;
import org.tech11.enums.ResponseMessage;

import javax.persistence.RollbackException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SQLConstraintExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    private static final Logger logger = LoggerFactory.getLogger(SQLConstraintExceptionHandler.class);

    @Override
    public Response toResponse(ConstraintViolationException ex) {

        BaseError baseError = new BaseError();
        baseError.setUrl("n/a");
        baseError.setErrorCode(Response.Status.CONFLICT.getStatusCode());
        baseError.setErrorMessage(ex.getMessage());

        ApiResponse<Object> errorResponse = new ApiResponse<>();
        errorResponse.setCode(ResponseMessage.FAILED.getCode());
        errorResponse.setMessage(ResponseMessage.FAILED.getMessage());
        errorResponse.setError(baseError);

        logger.error("[ HTTP ERROR:handleConstraintException {}", ex.getMessage());

        return Response.status(Response.Status.CONFLICT).entity(errorResponse).build();
    }
}