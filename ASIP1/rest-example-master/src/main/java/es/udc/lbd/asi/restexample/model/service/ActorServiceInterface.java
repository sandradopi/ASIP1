package es.udc.lbd.asi.restexample.model.service;

import java.util.List;

import es.udc.lbd.asi.restexample.model.service.dto.ActorDTO;


public interface ActorServiceInterface {
	public ActorDTO save(ActorDTO actor);
	public List<ActorDTO> findAll();
	
}
