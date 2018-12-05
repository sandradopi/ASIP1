package es.udc.lbd.asi.restexample.model.exception;

import es.udc.lbd.asi.restexample.web.exception.ModelException;

public class UserLoginEmailExistsException extends ModelException {
     public UserLoginEmailExistsException(String msg) {
         super(msg);
     }
 }