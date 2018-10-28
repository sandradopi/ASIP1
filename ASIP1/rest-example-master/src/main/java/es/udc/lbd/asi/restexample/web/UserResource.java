package es.udc.lbd.asi.restexample.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.management.InstanceNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.udc.lbd.asi.restexample.model.exception.UserLoginExistsException;
import es.udc.lbd.asi.restexample.model.service.UserService;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;
import es.udc.lbd.asi.restexample.model.service.dto.NormalUserDTO;
import es.udc.lbd.asi.restexample.model.service.dto.UserDTO;
import es.udc.lbd.asi.restexample.web.exception.RequestBodyNotValidException;


@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    private UserService userService;

    
    @GetMapping
    public List<NormalUserDTO> findAll() {
        return userService.findAll();
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
