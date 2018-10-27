package es.udc.lbd.asi.restexample.model.service;

import java.util.List;

import es.udc.lbd.asi.restexample.model.service.dto.GenreDTO;

public interface GenreServiceInterface {
	public GenreDTO save(GenreDTO genre);
	public List<GenreDTO> findAll();
}
