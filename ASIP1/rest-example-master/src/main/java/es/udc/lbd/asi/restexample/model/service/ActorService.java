package es.udc.lbd.asi.restexample.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.repository.ActorDAO;
import es.udc.lbd.asi.restexample.model.service.dto.ActorDTO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ActorService implements ActorServiceInterface {
	@Autowired
	private ActorDAO actorDAO;
	@Transactional(readOnly = false)
	public ActorDTO save(ActorDTO actor) {
		Actor bdActor = new Actor(actor.getName(), actor.getSurname1(), actor.getSurname2(), actor.getBirthdate());
		
		actorDAO.save(bdActor);
		return new ActorDTO(bdActor);
	}
	
}
