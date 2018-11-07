package es.udc.lbd.asi.restexample.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.Status;
import es.udc.lbd.asi.restexample.model.domain.TipoStatus;
import es.udc.lbd.asi.restexample.model.service.ActorService;
import es.udc.lbd.asi.restexample.model.service.StatusService;
import es.udc.lbd.asi.restexample.model.service.dto.ActorDTO;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;
import es.udc.lbd.asi.restexample.model.service.dto.StatusDTO;
import es.udc.lbd.asi.restexample.web.exception.IdAndBodyNotMatchingOnUpdateException;
import es.udc.lbd.asi.restexample.web.exception.InstanceNotFoundExceptionHIB;
import es.udc.lbd.asi.restexample.web.exception.RequestBodyNotValidException;

@RestController
@RequestMapping("/api/status/movies")
public class StatusResource {
	@Autowired
	private StatusService statusService;
	private final Logger logger = LoggerFactory.getLogger(AccountResource.class);

	@PostMapping("/{idMovie}/{statu}")
	public void save(@PathVariable Long idMovie, @PathVariable String statu)   {
			
			statusService.save(idMovie, TipoStatus.VISTA);
	
		

	}
	
	@GetMapping("/{idMovie}")
    public StatusDTO findStatus(@PathVariable Long idMovie){
			return statusService.findByMovieUser(idMovie);
			
    }
	
   @DeleteMapping("/{idMovie}/{statu}")
	public void delete(@PathVariable Long idMovie, @PathVariable String statu) {
	   //Solo permitimos borrar estados vistos si no han sido puntuados (La persona se equivoco al clickear checkbox vista)
			statusService.deleteByIdMovieUser(idMovie);

	}
	
	@PutMapping("/{idMovie}/{valoracion}")
    public StatusDTO update(@PathVariable Long idMovie, @PathVariable Integer valoracion){
        return statusService.update(idMovie,valoracion);
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
