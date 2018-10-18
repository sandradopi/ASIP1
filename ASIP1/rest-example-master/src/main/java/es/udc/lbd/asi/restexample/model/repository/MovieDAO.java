package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;
import javax.management.InstanceNotFoundException;
import es.udc.lbd.asi.restexample.model.domain.Movie;


public interface MovieDAO {
    public List<Movie> findAll();
    public Movie findById(Long id) throws InstanceNotFoundException;
    public Movie save(Movie movie);
    public void deleteById(Long idMovie);

}
