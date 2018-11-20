package es.udc.lbd.asi.restexample.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.service.ActorService;
import es.udc.lbd.asi.restexample.model.service.dto.ActorDTO;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;
import es.udc.lbd.asi.restexample.web.exception.IdAndBodyNotMatchingOnUpdateException;
import es.udc.lbd.asi.restexample.web.exception.InstanceNotFoundExceptionHIB;
import es.udc.lbd.asi.restexample.web.exception.RequestBodyNotValidException;

@RestController
@RequestMapping("/api/actors")
public class ActorResource {
	@Autowired
    private ActorService actorService;
	
	@PostMapping
    public ActorDTO save(@RequestBody @Valid ActorDTO actor, Errors errors) throws RequestBodyNotValidException {
        errorHandler(errors); 
        return actorService.save(actor);
    }
	
	@GetMapping
    public List<ActorDTO> findAll() {
        return actorService.findAll();
    }
	
	@GetMapping("/{idActor}")
    public ActorDTO findOne(@PathVariable Long idActor) throws InstanceNotFoundExceptionHIB{
        ActorDTO actor = actorService.findById(idActor);
    	return actor;
    }
	
	 @PutMapping("/{idActor}")
	    public ActorDTO update(@PathVariable Long idActor, @RequestBody @Valid ActorDTO actor, Errors errors)
	            throws IdAndBodyNotMatchingOnUpdateException, RequestBodyNotValidException {
	        errorHandler(errors);
	        if (idActor != actor.getIdActor()) {
	            throw new IdAndBodyNotMatchingOnUpdateException(Movie.class);
	        }
	        return actorService.update(actor);
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
