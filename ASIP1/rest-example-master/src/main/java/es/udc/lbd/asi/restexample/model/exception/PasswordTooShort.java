package es.udc.lbd.asi.restexample.model.exception;

import es.udc.lbd.asi.restexample.web.exception.ModelException;

public class PasswordTooShort extends ModelException {
     public PasswordTooShort(String msg) {
         super(msg);
     }
 }