package es.udc.lbd.asi.restexample.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import es.udc.lbd.asi.restexample.model.service.UserService;
import es.udc.lbd.asi.restexample.model.service.dto.NormalUserDTO;
import es.udc.lbd.asi.restexample.model.service.dto.NormalUserListUserDTO;
import es.udc.lbd.asi.restexample.model.service.dto.NormalUserRegistroDTO;
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
    
    @GetMapping("/LoginEmail") //Para en la pagina de registro cuando se carga no vamos a reutilizar findAll para traer todos los usuarios
    //para saber si el login y el email ya existen, si no que creamos un DTO nuevo que solo incluya los datos de login e email nada mas
    public List<NormalUserRegistroDTO> findAllEmailLogin() {
        return userService.findAllLoginEmail();
    }
    
    @GetMapping("/contadores") //Nos traemos una lista con todos los contadores de todos los usuarios
    public List<NormalUserListUserDTO> findAllContadores() {
        return userService.findAllContadores();
    }
    
    @GetMapping("detail/{login}") //Devolvemos los contadores de un usuario en concreto
    public NormalUserListUserDTO findOneContadores(@PathVariable String login) throws InstanceNotFoundExceptionHIB{
    	NormalUserListUserDTO user = userService.findByLoginContadores(login);
    	return user;
    }
    
    @GetMapping("/{idUser}")
    public NormalUserDTO findOne(@PathVariable Long idUser) throws InstanceNotFoundExceptionHIB{
        NormalUserDTO user = userService.findById(idUser);
    	return user;
    }
    
    @PutMapping("/{login}/{noti}") //Para cambiar el tipo de notificaciones del usuario
    public void update(@PathVariable String login, @PathVariable String noti) {
       userService.update(login,noti);
    }
    
  
    
}
