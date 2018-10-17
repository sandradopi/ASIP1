package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import javax.management.InstanceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import es.udc.lbd.asi.restexample.model.domain.Movie;



@Repository
public class MovieDAOMemory implements MovieDAO {

	@Autowired
	private NamedParameterJdbcOperations jdbcTemplate;

	public void setJdbcTemplate(NamedParameterJdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private static String FIND_ALL = "SELECT * "
			+ "FROM movies ";
	
	private static String FIND_BY_ID = "SELECT * "
			+ "FROM movies "
			+ "WHERE idMovie = :idMovie";
	
	
	@Override
	public List<Movie> findAll() {
		return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Movie.class));
	}

	@Override
	public Movie findById(Long idMovie) throws InstanceNotFoundException{
		return null;
		//jdbcTemplate.queryForObject(FIND_BY_ID, new Object[] { idMovie }, new RowMapper(Movie.class));;
	}


   
}
