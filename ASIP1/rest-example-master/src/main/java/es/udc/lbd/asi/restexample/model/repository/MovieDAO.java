package es.udc.lbd.asi.restexample.model.repository;

import java.util.Collection;
import java.util.List;

import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.Status;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;


public interface MovieDAO {
    public List<Movie> findAll();
    public Movie findById(Long id);
    public void save(Movie movie);
    public void deleteById(Long idMovie) ;
	public List<Movie> findAllVistas();
	public List<Movie> findAllPendientes();
	public List<Movie> findAllUser();
}
