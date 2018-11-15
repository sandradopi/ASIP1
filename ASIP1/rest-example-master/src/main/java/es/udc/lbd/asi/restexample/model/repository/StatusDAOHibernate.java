package es.udc.lbd.asi.restexample.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.NormalUser;
import es.udc.lbd.asi.restexample.model.domain.Status;
import es.udc.lbd.asi.restexample.repository.util.GenericDAOHibernate;

@Repository
public class StatusDAOHibernate extends GenericDAOHibernate implements StatusDAO {

	@Override
	public List<Status> findAll() {
		return getSession().createQuery("from Status").list();
	}
	
	@Override
	public void save(Status status) {
		getSession().saveOrUpdate(status);
	}

	@Override
	public Status findById(Long idStatus) {
		 return (Status) getSession().createQuery("from Status p where p.idStatus = :idStatus").setParameter("idStatus", idStatus).uniqueResult();
	}

	@Override
	public Status findByMovieUser(Movie movie, NormalUser user) {
		 return (Status) getSession().createQuery("from Status p where p.movie = :movie AND p.user=:user").setParameter("movie", movie).setParameter("user", user).uniqueResult();
	}
	@Override
	public Status findByMovie(Movie movie) {
		 return (Status) getSession().createQuery("from Status p where p.movie = :movie").setParameter("movie", movie).uniqueResult();
	}
	
	@Override
	public void delete(Status status) {
		  getSession().delete(findById(status.getIdStatus()));
		
	}

	

	

	
}
