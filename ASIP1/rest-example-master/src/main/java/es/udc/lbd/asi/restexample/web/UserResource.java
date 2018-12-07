package es.udc.lbd.asi.restexample.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.UserNoti;
import es.udc.lbd.asi.restexample.model.service.UserService;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;
import es.udc.lbd.asi.restexample.model.service.dto.NormalUserDTO;
import es.udc.lbd.asi.restexample.model.service.dto.NormalUserListUserDTO;
import es.udc.lbd.asi.restexample.web.exception.IdAndBodyNotMatchingOnUpdateException;
import es.udc.lbd.asi.restexample.web.exception.InstanceNotFoundExceptionHIB;
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
    
    @GetMapping("/contadores")
    public List<NormalUserListUserDTO> findAllContadores() {
        return userService.findAllContadores();
    }
    
    @GetMapping("detail/{login}")
    public NormalUserListUserDTO findOneContadores(@PathVariable String login) throws InstanceNotFoundExceptionHIB{
    	NormalUserListUserDTO user = userService.findByLoginContadores(login);
    	return user;
    }
    
    @GetMapping("/{idUser}")
    public NormalUserDTO findOne(@PathVariable Long idUser) throws InstanceNotFoundExceptionHIB{
        NormalUserDTO user = userService.findById(idUser);
    	return user;
    }
    
    @PutMapping("/{login}/{noti}")
    public void update(@PathVariable String login, @PathVariable String noti) {
       
       userService.update(login,noti);
    }
    
    @GetMapping("/list/imagenes/{login}")
    public @ResponseBody byte[] findImagenes(@PathVariable String login) throws IOException {
    	InputStream in = getClass().getClassLoader().getResourceAsStream("WEB-INF/images1/"+login+".jpg");
        return IOUtils.toByteArray(in);
    }
  
    
}
