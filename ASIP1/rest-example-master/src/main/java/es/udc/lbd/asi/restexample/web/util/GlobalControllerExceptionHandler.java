package es.udc.lbd.asi.restexample.web.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.udc.lbd.asi.restexample.web.exception.IdAndBodyNotMatchingOnUpdateException;
import es.udc.lbd.asi.restexample.web.exception.ResourceException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	 @ResponseStatus(HttpStatus.NOT_FOUND)
	    @ExceptionHandler(ResourceException.class)
	    public ErrorDTO resourceExceptionHandler(ResourceException e) {
	        return new ErrorDTO(e.getMessage());
	    }
}
