package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;
import es.udc.lbd.asi.restexample.model.domain.Movie;

public interface MovieDAO {
    public List<Movie> findAll();
    public Movie findById(Long id);

}
