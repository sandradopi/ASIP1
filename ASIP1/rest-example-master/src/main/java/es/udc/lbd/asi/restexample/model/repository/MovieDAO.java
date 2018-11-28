package es.udc.lbd.asi.restexample.model.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.NormalUser;
import es.udc.lbd.asi.restexample.model.domain.Status;
import es.udc.lbd.asi.restexample.model.domain.TipoStatus;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;


public interface MovieDAO {
    public List<Movie> findAll();
    public Movie findById(Long id);
    public void save(Movie movie);
    public void deleteById(Long idMovie) ;
	public List<Movie> findAllVistasVote(NormalUser usuarioNormal);
	public List<Movie> findAllMoviesType(NormalUser usuarioNorma, TipoStatus tipo);
	public List<Movie> findAllUser();
	public List<Movie> findAllDatePendiente(Date data);
	public Long findByMovieUserVistaPendiente(String login, TipoStatus tipo);
	//public Long findByMovieUserPendiente(String login);
	public Long findByMovieUserVistaValoration(String login);
}
