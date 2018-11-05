package es.udc.lbd.asi.restexample.model.service;

import es.udc.lbd.asi.restexample.model.domain.TipoStatus;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;
import es.udc.lbd.asi.restexample.model.service.dto.NormalUserDTO;
import es.udc.lbd.asi.restexample.model.service.dto.StatusDTO;


public interface StatusServiceInterface {
	public void save(Long MovieId, TipoStatus STATE);
	public StatusDTO update(Long idMovie, Integer valoracion);
	public void deleteByIdMovieUser(Long idMovie);
	public StatusDTO findByMovieUser(Long idMovie);
	
}
