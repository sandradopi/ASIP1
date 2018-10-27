package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import es.udc.lbd.asi.restexample.model.domain.Genre;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.repository.util.GenericDAOHibernate;

@Repository
public class GenreDAOHibernate extends GenericDAOHibernate implements GenreDAO {

	@Override
	public void save(Genre genre) {
		getSession().saveOrUpdate(genre);
	}
	
	@Override
	public Genre findById(Long idGenre) {
		 return (Genre) getSession().createQuery("from Genre p where p.idGenre = :idGenre").setParameter("idGenre", idGenre).uniqueResult();
	}
	
	@Override
	public List<Genre> findAll() {
		return getSession().createQuery("from Genre").list();
	}
	
}
