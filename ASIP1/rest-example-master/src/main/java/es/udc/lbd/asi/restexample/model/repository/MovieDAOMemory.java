package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import javax.management.InstanceNotFoundException;
import org.springframework.stereotype.Repository;
import es.udc.lbd.asi.restexample.model.domain.Movie;



@Repository
public class MovieDAOMemory implements MovieDAO {

	
	@Override
	public List<Movie> findAll() {
		return null;
	}

	@Override
	public Movie findById(Long idMovie) throws InstanceNotFoundException{
		return null;
		
	}


   
}
