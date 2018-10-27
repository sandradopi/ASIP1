package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import es.udc.lbd.asi.restexample.model.domain.Actor;

public interface ActorDAO {
	public void save(Actor actor);
	 public Actor findById(Long id);
	 public List<Actor> findAll();
}
