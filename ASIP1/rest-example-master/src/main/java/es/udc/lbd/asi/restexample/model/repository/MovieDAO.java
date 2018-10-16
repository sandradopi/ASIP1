package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.Post;

public interface MovieDAO {
    List<Movie> findAll();

    Movie findById(Long id);

}
