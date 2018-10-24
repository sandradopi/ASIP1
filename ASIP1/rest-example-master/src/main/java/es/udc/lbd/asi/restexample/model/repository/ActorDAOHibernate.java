package es.udc.lbd.asi.restexample.model.repository;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.repository.util.GenericDAOHibernate;

public class ActorDAOHibernate extends GenericDAOHibernate implements ActorDAO {

	@Override
	public void save(Actor actor) {
		getSession().saveOrUpdate(actor);
	}
	
}
