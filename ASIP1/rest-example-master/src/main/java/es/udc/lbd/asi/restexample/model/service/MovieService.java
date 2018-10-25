package es.udc.lbd.asi.restexample.model.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Director;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.repository.ActorDAO;
import es.udc.lbd.asi.restexample.model.repository.DirectorDAO;
import es.udc.lbd.asi.restexample.model.repository.GenreDAO;
import es.udc.lbd.asi.restexample.model.repository.MovieDAO;
import es.udc.lbd.asi.restexample.model.service.dto.ActorDTO;
import es.udc.lbd.asi.restexample.model.service.dto.DirectorDTO;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;



@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MovieService implements MovieServiceInterface{

  @Autowired
  private MovieDAO movieDAO;
  @Autowired
  private GenreDAO genreDAO;
  @Autowired
  private ActorDAO actorDAO;
  @Autowired
  private DirectorDAO directorDAO;

    public List<MovieDTO> findAll() {
    	return movieDAO.findAll().stream().map(movie -> new MovieDTO(movie)).collect(Collectors.toList());
    }

    public MovieDTO findById(Long idMovie)  {
    	 return new MovieDTO(movieDAO.findById(idMovie));
    }
    
    @Transactional(readOnly = false)
    public MovieDTO save(MovieDTO movie) {	
    	Movie bdMovie = new Movie(movie.getHidden(), movie.getName(), movie.getSummary(), movie.getDuration(), movie.getData());
    	bdMovie.setGenre(genreDAO.findById(movie.getGenre().getIdGenre()));
    	
    	Set<Actor> auxiliarA = new HashSet<Actor>();
    	Set<Director> auxiliarD = new HashSet<Director>();
    	
    	
    	for(ActorDTO a: movie.getParticipantes()){
    		auxiliarA.add(actorDAO.findById(a.getIdActor()));
        }
    	
    	for(DirectorDTO d: movie.getDirigentes()){
    		auxiliarD.add(directorDAO.findById(d.getIdDirector()));
        }
    	
    	bdMovie.setParticipantes(auxiliarA);
    	bdMovie.setDirigentes(auxiliarD);
    	movieDAO.save(bdMovie);
        return new MovieDTO(bdMovie);
    }
    
    
    
    @Transactional(readOnly = false)
    public MovieDTO update(MovieDTO movie){
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
