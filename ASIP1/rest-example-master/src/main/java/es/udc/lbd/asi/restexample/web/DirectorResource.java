package es.udc.lbd.asi.restexample.web;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import es.udc.lbd.asi.restexample.model.service.DirectorService;
import es.udc.lbd.asi.restexample.model.service.dto.DirectorDTO;
import es.udc.lbd.asi.restexample.web.exception.RequestBodyNotValidException;

@RequestMapping("/api/directors")
public class DirectorResource {
	@Autowired
    private DirectorService directorService;
	
	 @PostMapping
	    public DirectorDTO save(@RequestBody @Valid DirectorDTO director, Errors errors) throws RequestBodyNotValidException {
	        errorHandler(errors); 
	        return directorService.save(director);
	    }
	    
	    private void errorHandler(Errors errors) throws RequestBodyNotValidException {
	        if (errors.hasErrors()) {
	            String errorMsg = errors.getFieldErrors().stream()
	                    .map(fe -> String.format("%s.%s %s", fe.getObjectName(), fe.getField(), fe.getDefaultMessage()))
	                    .collect(Collectors.joining("; "));
	            throw new RequestBodyNotValidException(errorMsg);
	        }
	    }
}
