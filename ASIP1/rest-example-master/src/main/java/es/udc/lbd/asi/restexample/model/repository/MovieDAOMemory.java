package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.management.InstanceNotFoundException;
import org.springframework.stereotype.Repository;
import es.udc.lbd.asi.restexample.model.domain.Movie;

@Repository
public class MovieDAOMemory implements MovieDAO {

	
	@Override
	public List<Movie> findAll() {
		return InMemoryDB.movie.values().stream().collect(Collectors.toList());
	}

	@Override
	public Movie findById(Long idMovie) throws InstanceNotFoundException{
		 return InMemoryDB.movie.get(idMovie);
		 
	}
	 @Override
	    public Movie save(Movie movie) {
	        if (movie.getIdMovie() == null) {
	            movie.setIdMovie(InMemoryDB.idGenerator.addAndGet(1));
	        }
	        InMemoryDB.movie.put(movie.getIdMovie(), movie);
	        return movie;
	    }

	    @Override
	    public void deleteById(Long id) {
	        InMemoryDB.movie.remove(id);
	    }


   
}
