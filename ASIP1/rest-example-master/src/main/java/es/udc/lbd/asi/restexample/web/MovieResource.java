package es.udc.lbd.asi.restexample.web;

import java.util.List;

import javax.management.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.service.MovieService;


@RestController
@RequestMapping("/api/movies")
public class MovieResource {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> findAll() {
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public Movie findOne(@PathVariable Long id) throws InstanceNotFoundException {
        return movieService.findById(id);
    }

    
}
