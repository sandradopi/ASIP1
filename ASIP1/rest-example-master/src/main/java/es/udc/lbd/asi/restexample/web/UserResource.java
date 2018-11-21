package es.udc.lbd.asi.restexample.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.udc.lbd.asi.restexample.model.service.UserService;
import es.udc.lbd.asi.restexample.model.service.dto.ActorDTO;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;
import es.udc.lbd.asi.restexample.model.service.dto.NormalUserDTO;
import es.udc.lbd.asi.restexample.model.service.dto.UserDTO;
import es.udc.lbd.asi.restexample.web.exception.InstanceNotFoundExceptionHIB;



@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    private UserService userService;

    
    @GetMapping
    public List<NormalUserDTO> findAll() {
        return userService.findAll();
    }
    
    @GetMapping("/{idUser}")
    public NormalUserDTO findOne(@PathVariable Long idUser) throws InstanceNotFoundExceptionHIB{
        NormalUserDTO user = userService.findById(idUser);
    	return user;
    }
  
}
