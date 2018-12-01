package es.udc.lbd.asi.restexample.model.repository;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.NormalUser;
import es.udc.lbd.asi.restexample.model.domain.Status;
import es.udc.lbd.asi.restexample.model.domain.TipoStatus;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;
import es.udc.lbd.asi.restexample.repository.util.GenericDAOHibernate;


@Repository
public class MovieDAOHibernate extends GenericDAOHibernate implements MovieDAO {
	
	@Override
	public List<Movie> findAll() {
		return getSession().createQuery("from Movie").list();
	}

	@Override
	public Movie findById(Long idMovie) {
		 return (Movie) getSession().createQuery("from Movie p where p.idMovie = :idMovie").setParameter("idMovie", idMovie).uniqueResult();
		 
	}
	 @Override
	 public void save(Movie movie) {
		 getSession().saveOrUpdate(movie);
	 }

	 @Override
	 public void deleteById(Long idMovie) {
	    getSession().delete(findById(idMovie));
	 }
	 

		@Override
		public List<Movie> findAllMoviesType(NormalUser usuarioNormal, TipoStatus tipo) {
			return getSession().createQuery("select movie from Status as s inner join s.movie as movie WHERE s.movie = movie AND s.type=:tipo AND movie.hidden=false AND s.user= :usuarioNormal").setParameter("usuarioNormal", usuarioNormal).setParameter("tipo", tipo).list();
			
		}

		@Override
		public List<Movie> findAllUser() {
			return getSession().createQuery("from Movie m where m.hidden=false").list();
			}

		@Override
		public List<Movie> findAllVistasVote(NormalUser usuarioNormal) {
			return getSession().createQuery("select movie from Status as s inner join s.movie as movie WHERE s.movie = movie AND s.type='VISTA' AND movie.hidden=false AND s.valoration=null AND s.user= :usuarioNormal").setParameter("usuarioNormal", usuarioNormal).list();
		}
		
		@Override
		public List<Movie> findAllDatePendiente(Date data) {
			return getSession().createQuery("select DISTINCT movie from Status as s inner join s.movie as movie WHERE s.movie = movie AND s.type='PENDIENTE' AND movie.data = :data").setParameter("data", data).list();
			
		}
		@Override
		public Long findByMovieUserVistaPendiente(String login, TipoStatus tipo) {
			 return (Long) getSession().createQuery("select count(*) from Status p where p.user.login=:login AND p.type=:tipo").setParameter("login", login).setParameter("tipo", tipo).uniqueResult();
		}
		
		@Override
		public Long findByMovieUserVistaValoration(String login) {
			 return (Long) getSession().createQuery("select count(*) from Status p where p.user.login=:login AND p.type='VISTA' AND p.valoration <> 0").setParameter("login", login).uniqueResult();
		}

		
		
		

	   
}
