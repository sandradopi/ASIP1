package es.udc.lbd.asi.restexample.model.exception;

import es.udc.lbd.asi.restexample.web.exception.ModelException;

public class EmailIncorrect extends ModelException {
     public EmailIncorrect(String msg) {
         super(msg);
     }
 }