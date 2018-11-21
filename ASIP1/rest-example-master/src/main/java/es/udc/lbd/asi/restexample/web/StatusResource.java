package es.udc.lbd.asi.restexample.web;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.udc.lbd.asi.restexample.model.service.StatusService;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;
import es.udc.lbd.asi.restexample.model.service.dto.StatusDTO;
import es.udc.lbd.asi.restexample.web.exception.InstanceNotFoundExceptionHIB;

@RestController
@RequestMapping("/api/status/movies")
public class StatusResource {
	@Autowired
	private StatusService statusService;


	@PostMapping("/{idMovie}/{statu}")
	public void save(@PathVariable Long idMovie, @PathVariable String statu)   {
			statusService.save(idMovie, statu);

	}
	
	@GetMapping("/{idMovie}")
    public StatusDTO findStatus(@PathVariable Long idMovie) throws InstanceNotFoundExceptionHIB{
			return statusService.findByMovieUser(idMovie);
			
    }
	
   @DeleteMapping("/{idMovie}")
	public void delete(@PathVariable Long idMovie) throws InstanceNotFoundExceptionHIB{
	   //Solo permitimos borrar estados vistos si no han sido puntuados (La persona se equivoco al clickear checkbox vista)
			statusService.deleteByIdMovieUser(idMovie);

	}
	
	@PutMapping("/{idMovie}/{valoracion}")
    public StatusDTO update(@PathVariable Long idMovie, @PathVariable Integer valoracion){
        return statusService.update(idMovie,valoracion);
    }
	
	


}
