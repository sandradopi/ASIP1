package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import es.udc.lbd.asi.restexample.model.domain.Director;
import es.udc.lbd.asi.restexample.model.domain.Genre;
import es.udc.lbd.asi.restexample.repository.util.GenericDAOHibernate;

@Repository
public class DirectorDAOHibernate extends GenericDAOHibernate implements DirectorDAO {

	@Override
	public void save(Director director) {
		getSession().saveOrUpdate(director);
	}
	
	@Override
	public Director findById(Long idDirector) {
		 return (Director) getSession().createQuery("from Director p where p.idDirector = :idDirector").setParameter("idDirector", idDirector).uniqueResult();
	}
	
	@Override
	public List<Director> findAll() {
		return getSession().createQuery("from Director").list();
	}
}
