package es.udc.lbd.asi.restexample.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.repository.MovieDAO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MovieService {

    @Autowired
    private MovieDAO movieDAO;

    public List<Movie> findAll() {
        return movieDAO.findAll();
    }

    public Movie findById(Long id) {
        return movieDAO.findById(id);
    }

}
