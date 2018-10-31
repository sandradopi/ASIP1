package es.udc.lbd.asi.restexample.model.service;

import es.udc.lbd.asi.restexample.model.domain.TipoStatus;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;
import es.udc.lbd.asi.restexample.model.service.dto.StatusDTO;

public interface StatusServiceInterface {
	public void save(MovieDTO movie, TipoStatus STATE);
	
}
