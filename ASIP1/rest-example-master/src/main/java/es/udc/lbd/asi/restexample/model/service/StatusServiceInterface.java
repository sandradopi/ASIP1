package es.udc.lbd.asi.restexample.model.service;


import java.text.ParseException;

import es.udc.lbd.asi.restexample.model.service.dto.StatusDTO;


public interface StatusServiceInterface {
	public void save(Long MovieId, String statu) throws ParseException;
	public StatusDTO update(Long idMovie, Integer valoracion);
	public void deleteByIdMovieUser(Long idMovie);
	public StatusDTO findByMovieUser(Long idMovie);
	
	
}
