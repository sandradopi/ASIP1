package es.udc.lbd.asi.restexample.web.exception;

public class RequestBodyNotValidException extends ResourceException {
    public RequestBodyNotValidException(String errorMsg) {
        super(errorMsg);
    }
}
