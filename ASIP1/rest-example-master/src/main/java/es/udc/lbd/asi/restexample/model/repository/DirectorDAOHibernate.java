package es.udc.lbd.asi.restexample.model.repository;

import es.udc.lbd.asi.restexample.model.domain.Director;
import es.udc.lbd.asi.restexample.repository.util.GenericDAOHibernate;

public class DirectorDAOHibernate extends GenericDAOHibernate implements DirectorDAO {

	@Override
	public void save(Director director) {
		getSession().saveOrUpdate(director);
	}
}
