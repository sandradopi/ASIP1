package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import es.udc.lbd.asi.restexample.model.domain.Movie;


public interface MovieDAO {
    public List<Movie> findAll();
    public Movie findById(Long id);
    public void save(Movie movie);
    public void deleteById(Long idMovie) ;
}
