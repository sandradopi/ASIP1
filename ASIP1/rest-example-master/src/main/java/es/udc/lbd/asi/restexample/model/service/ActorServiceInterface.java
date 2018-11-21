package es.udc.lbd.asi.restexample.model.service;

import java.util.List;

import es.udc.lbd.asi.restexample.model.service.dto.ActorDTO;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;


public interface ActorServiceInterface {
	public ActorDTO save(ActorDTO actor);
	public List<ActorDTO> findAll();
	public ActorDTO findById(Long idActor);
	public ActorDTO update(ActorDTO actor);
	public String deleteById(Long idActor);
	
}
