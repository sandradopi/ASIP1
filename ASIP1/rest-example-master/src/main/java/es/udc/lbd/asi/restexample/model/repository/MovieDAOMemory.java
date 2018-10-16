package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.Post;

@Repository
public class MovieDAOMemory implements MovieDAO {

	@Override
	public List<Movie> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movie findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


   
}
