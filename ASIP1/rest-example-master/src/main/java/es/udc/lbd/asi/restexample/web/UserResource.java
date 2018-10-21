package es.udc.lbd.asi.restexample.web;

import java.util.List;

import javax.management.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.User;
import es.udc.lbd.asi.restexample.model.service.MovieService;
import es.udc.lbd.asi.restexample.model.service.UserService;
import es.udc.lbd.asi.restexample.web.exception.IdAndBodyNotMatchingOnUpdateException;


@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    private UserService userService;

    
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }
 
    @GetMapping("/{login}")
    public User findOne(@PathVariable String login) throws InstanceNotFoundException {
        return userService.findByLogin(login);
    }
    
    @PostMapping
    public User save(@RequestBody User user) {
        return userService.save(user);
    }
    
 
    
}
