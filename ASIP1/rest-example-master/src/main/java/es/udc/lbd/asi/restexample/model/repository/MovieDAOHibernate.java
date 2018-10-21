package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.management.InstanceNotFoundException;
import org.springframework.stereotype.Repository;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.repository.util.GenericDAOHibernate;


@Repository
public class MovieDAOHibernate extends GenericDAOHibernate implements MovieDAO {

	
	@Override
	public List<Movie> findAll() {
		return getSession().createQuery("from Movie").list();
	}

	@Override
	public Movie findById(Long idMovie){
		 return (Movie) getSession().createQuery("from Movie p where p.idMovie = :idMovie").setParameter("idMovie", idMovie).uniqueResult();
		 
	}
	 @Override
	    public void save(Movie movie) {
		  getSession().saveOrUpdate(movie);
	    }

	    @Override
	    public void deleteById(Long idMovie){
	    	getSession().delete(findById(idMovie));
	    }


   
}
