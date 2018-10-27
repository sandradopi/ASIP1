package es.udc.lbd.asi.restexample.model.exception;

import es.udc.lbd.asi.restexample.web.exception.ModelException;

public class UserLoginExistsException extends ModelException {
     public UserLoginExistsException(String msg) {
         super(msg);
     }
 }