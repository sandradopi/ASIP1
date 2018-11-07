package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.NormalUser;
import es.udc.lbd.asi.restexample.model.domain.Status;
import es.udc.lbd.asi.restexample.model.service.dto.StatusDTO;

public interface StatusDAO {
	 public void save(Status status);
	 public Status findById(Long id);
	 public void delete(Status status);
	 public Status findByMovieUser(Movie movie, NormalUser usuario);
	 public Status findByMovie(Movie movie);
	 public List<Status> findAll();
 
}
