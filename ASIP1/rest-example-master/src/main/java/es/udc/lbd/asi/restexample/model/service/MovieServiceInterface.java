package es.udc.lbd.asi.restexample.model.service;

import java.util.List;

import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;

public interface MovieServiceInterface {
    public List<MovieDTO> findAll();
    public MovieDTO findById(Long id);
    public MovieDTO save(MovieDTO movie);
    public void deleteById(Long idMovie);
    public MovieDTO update(MovieDTO movie);
	List<MovieDTO> findAllMoviesType(String tipo);
	//List<MovieDTO> findAllVistas();
	List<MovieDTO> findAllVistasVote();
}
