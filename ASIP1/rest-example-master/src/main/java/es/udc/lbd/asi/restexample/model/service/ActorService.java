package es.udc.lbd.asi.restexample.model.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Director;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.repository.ActorDAO;
import es.udc.lbd.asi.restexample.model.repository.MovieDAO;
import es.udc.lbd.asi.restexample.model.service.dto.ActorDTO;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ActorService implements ActorServiceInterface {
	@Autowired
	private ActorDAO actorDAO;
	@Autowired
	private MovieDAO movieDAO;
	private Set<Movie> auxiliarM = new HashSet<Movie>();
	
	@Transactional(readOnly = false)
	public ActorDTO save(ActorDTO actor) {
		Actor bdActor = new Actor(actor.getName(), actor.getSurname1(), actor.getSurname2(), actor.getBirthdate());
		for(MovieDTO m: actor.getActuations()){
    		auxiliarM.add(movieDAO.findById(m.getIdMovie()));
        }
		bdActor.setActuations(auxiliarM);
		actorDAO.save(bdActor);
		return new ActorDTO(bdActor);
	}
	
}
