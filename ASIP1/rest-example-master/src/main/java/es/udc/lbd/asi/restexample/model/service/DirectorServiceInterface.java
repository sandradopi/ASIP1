package es.udc.lbd.asi.restexample.model.service;

import java.util.List;

import es.udc.lbd.asi.restexample.model.service.dto.ActorDTO;
import es.udc.lbd.asi.restexample.model.service.dto.DirectorDTO;

public interface DirectorServiceInterface {
	
	public DirectorDTO save(DirectorDTO director);
	public List<DirectorDTO> findAll();
	public DirectorDTO findById(Long idDirector);
	public DirectorDTO update(DirectorDTO director);
	public String deleteById(Long idDirector);
}
