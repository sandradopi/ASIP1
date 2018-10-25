package es.udc.lbd.asi.restexample.model.repository;

import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.Status;
import es.udc.lbd.asi.restexample.repository.util.GenericDAOHibernate;

@Repository
public class StatusDAOHibernate extends GenericDAOHibernate implements StatusDAO {

	@Override
	public void save(Status status) {
		getSession().saveOrUpdate(status);
	}

	@Override
	public Status findById(Long idStatus) {
		 return (Status) getSession().createQuery("from Status p where p.idStatus = :idStatus").setParameter("idStatus", idStatus).uniqueResult();
	}
	
}
