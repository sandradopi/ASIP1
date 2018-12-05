package es.udc.lbd.asi.restexample.model.exception;

import es.udc.lbd.asi.restexample.web.exception.ModelException;

public class RequiredFieldsException extends ModelException {
     public RequiredFieldsException(String msg) {
         super(msg);
     }
 }