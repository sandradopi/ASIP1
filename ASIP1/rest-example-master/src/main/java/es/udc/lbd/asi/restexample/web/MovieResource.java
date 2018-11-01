package es.udc.lbd.asi.restexample.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
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
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;
import es.udc.lbd.asi.restexample.web.exception.IdAndBodyNotMatchingOnUpdateException;
import es.udc.lbd.asi.restexample.web.exception.InstanceNotFoundExceptionHIB;
import es.udc.lbd.asi.restexample.web.exception.RequestBodyNotValidException;



@RestController
@RequestMapping("/api/movies")
public class MovieResource {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<MovieDTO> findAll() {
        return movieService.findAll();
    }

    @GetMapping("/{idMovie}")
    public MovieDTO findOne(@PathVariable Long idMovie) throws InstanceNotFoundExceptionHIB{
        MovieDTO movie = movieService.findById(idMovie);
    	return movie;
    }
    
    @PostMapping
    public MovieDTO save(@RequestBody @Valid MovieDTO movie, Errors errors) throws RequestBodyNotValidException {
        errorHandler(errors); 
        return movieService.save(movie);
    }
    
    @PutMapping("/{idMovie}")
    public MovieDTO update(@PathVariable Long idMovie, @RequestBody @Valid MovieDTO movie, Errors errors)
            throws IdAndBodyNotMatchingOnUpdateException, RequestBodyNotValidException {
        errorHandler(errors);
        if (idMovie != movie.getIdMovie()) {
            throw new IdAndBodyNotMatchingOnUpdateException(Movie.class);
        }
        return movieService.update(movie);
    }

    @DeleteMapping("/{idMovie}")
    public void delete(@RequestParam Long idMovie) throws InstanceNotFoundExceptionHIB{
        movieService.deleteById(idMovie);
    }
    
    private void errorHandler(Errors errors) throws RequestBodyNotValidException {
        if (errors.hasErrors()) {
            String errorMsg = errors.getFieldErrors().stream()
                    .map(fe -> String.format("%s.%s %s", fe.getObjectName(), fe.getField(), fe.getDefaultMessage()))
                    .collect(Collectors.joining("; "));
            throw new RequestBodyNotValidException(errorMsg);
        }
    }
    
}
