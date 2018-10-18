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


   
}
