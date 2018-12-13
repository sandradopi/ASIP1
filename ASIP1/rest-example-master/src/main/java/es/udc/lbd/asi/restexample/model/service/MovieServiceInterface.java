package es.udc.lbd.asi.restexample.model.service;

import java.util.List;

import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;
import es.udc.lbd.asi.restexample.model.service.dto.MovieListDTO;

public interface MovieServiceInterface {
    public List<MovieDTO> findAll();
    public List<MovieListDTO> findAllMedia();
    public MovieDTO findById(Long id);
    public MovieDTO save(MovieDTO movie);
    public void deleteById(Long idMovie);
    public MovieDTO update(MovieDTO movie);
	public List<MovieListDTO> findAllMoviesType(String tipo, String login);
	public List<MovieListDTO> findAllVistasVote();
	public Long findAverage(Long idMovie);
}
