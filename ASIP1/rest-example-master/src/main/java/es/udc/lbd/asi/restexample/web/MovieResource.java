package es.udc.lbd.asi.restexample.web;

import java.util.List;

import javax.management.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.service.MovieService;
import es.udc.lbd.asi.restexample.web.exception.IdAndBodyNotMatchingOnUpdateException;


@RestController
@RequestMapping("/api/movies")
public class MovieResource {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> findAll() {
        return movieService.findAll();
    }

    @GetMapping("/{idMovie}")
    public Movie findOne(@PathVariable Long idMovie) throws InstanceNotFoundException {
        return movieService.findById(idMovie);
    }
    
    @PostMapping
    public Movie save(@RequestBody Movie movie) {
        return movieService.save(movie);
    }
    
    @PutMapping("/{idMovie}")
    public Movie update(@PathVariable Long idMovie, @RequestBody Movie movie) throws IdAndBodyNotMatchingOnUpdateException {
        if (idMovie != movie.getIdMovie()) {
            throw new IdAndBodyNotMatchingOnUpdateException(Movie.class);
        }
        return movieService.save(movie);
    }

    @DeleteMapping("/{idMovie}")
    public void delete(@RequestParam Long idMovie) {
        movieService.deleteById(idMovie);
    }
    
}
