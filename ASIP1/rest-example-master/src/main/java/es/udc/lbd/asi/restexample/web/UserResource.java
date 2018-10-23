package es.udc.lbd.asi.restexample.web;

import java.util.List;

import javax.management.InstanceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.udc.lbd.asi.restexample.model.service.UserService;
import es.udc.lbd.asi.restexample.model.service.dto.UserDTO;


@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    private UserService userService;

    
    @GetMapping
    public List<UserDTO> findAll() {
        return userService.findAll();
    }
 
    @GetMapping("/{login}")
    public UserDTO findOne(@PathVariable String login) throws InstanceNotFoundException{
        return userService.findByLogin(login);
    }
    
    @PostMapping
    public UserDTO save(@RequestBody UserDTO user) {
        return userService.save(user);
    }
    
 
    
}
