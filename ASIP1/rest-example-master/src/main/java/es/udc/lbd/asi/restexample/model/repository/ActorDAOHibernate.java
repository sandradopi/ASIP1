package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Genre;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.repository.util.GenericDAOHibernate;

@Repository
public class ActorDAOHibernate extends GenericDAOHibernate implements ActorDAO {

	@Override
	public void save(Actor actor) {
		getSession().saveOrUpdate(actor);
	}

	@Override
	public Actor findById(Long idActor) {
		 return (Actor) getSession().createQuery("from Actor p where p.idActor = :idActor").setParameter("idActor", idActor).uniqueResult();
	}
	
	@Override
	public List<Actor> findAll() {
		return getSession().createQuery("from Actor").list();
	}
	
	@Override
	 public void deleteById(Long idActor) {
	    getSession().delete(findById(idActor));
	 }
	
}
