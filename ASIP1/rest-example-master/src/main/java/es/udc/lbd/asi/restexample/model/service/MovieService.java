package es.udc.lbd.asi.restexample.model.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.management.InstanceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.repository.MovieDAO;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;



@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MovieService implements MovieServiceInterface{

  @Autowired
    private MovieDAO movieDAO;

    public List<MovieDTO> findAll() {
    	return movieDAO.findAll().stream().map(movie -> new MovieDTO(movie)).collect(Collectors.toList());
    }

    public MovieDTO findById(Long idMovie) throws InstanceNotFoundException {
    	 return new MovieDTO(movieDAO.findById(idMovie));
    }
    
    @Transactional(readOnly = false)
    public MovieDTO save(MovieDTO movie) {
    	Movie bdMovie = new Movie(movie.getName(), movie.getSummary(), movie.getDuration(), movie.getData());
        movieDAO.save(bdMovie);
        return new MovieDTO(bdMovie);
    }
    
    @Transactional(readOnly = false)
    public MovieDTO update(MovieDTO movie) {
        Movie bdMovie = movieDAO.findById(movie.getIdMovie());
        bdMovie.setName(movie.getName());
        bdMovie.setSummary(movie.getSummary());
        bdMovie.setDuration(movie.getDuration());
        bdMovie.setData(movie.getData());
        movieDAO.save(bdMovie);
        return new MovieDTO(bdMovie);
        }

    @Transactional(readOnly = false)
    public void deleteById(Long idMovie) {
    	movieDAO.deleteById(idMovie);
    }


}
