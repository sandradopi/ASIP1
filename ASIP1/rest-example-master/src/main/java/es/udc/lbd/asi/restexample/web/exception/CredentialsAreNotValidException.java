package es.udc.lbd.asi.restexample.web.exception;

 public class CredentialsAreNotValidException extends ResourceException {

     public CredentialsAreNotValidException(String errorMsg) {
         super(errorMsg);
     }
 } 