package es.udc.lbd.asi.restexample.model.service;

import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.TipoStatus;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;
import es.udc.lbd.asi.restexample.model.service.dto.StatusDTO;

public interface StatusServiceInterface {
	public void save(Long MovieId, TipoStatus STATE);
	public void deleteByIdMovieUser(Long MovieId);
	
}
