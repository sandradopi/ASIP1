package es.udc.lbd.asi.restexample.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.Status;
import es.udc.lbd.asi.restexample.model.repository.ActorDAO;
import es.udc.lbd.asi.restexample.model.service.dto.ActorDTO;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ActorService implements ActorServiceInterface {
	@Autowired
	private ActorDAO actorDAO;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@Transactional(readOnly = false)
	public ActorDTO save(ActorDTO actor) {
		Actor bdActor = new Actor(actor.getName(), actor.getSurname1(), actor.getSurname2(), actor.getBirthdate());
		
		actorDAO.save(bdActor);
		return new ActorDTO(bdActor);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<ActorDTO> findAll() {
    	return actorDAO.findAll().stream().map(actor-> new ActorDTO(actor)).collect(Collectors.toList());
    }
	
	@PreAuthorize("hasAuthority('ADMIN')")
	public ActorDTO findById(Long idActor)  {
   	 return new ActorDTO(actorDAO.findById(idActor));
   }
	
	 @PreAuthorize("hasAuthority('ADMIN')")
	    @Transactional(readOnly = false)
	    @Override
	    public ActorDTO update(ActorDTO actor){
	        Actor bdActor = actorDAO.findById(actor.getIdActor());
	        bdActor.setName(actor.getName());
	        bdActor.setSurname1(actor.getSurname1());
	        bdActor.setSurname2(actor.getSurname2());
	        bdActor.setBirthdate(actor.getBirthdate());
	        
	       
	      
	        actorDAO.save(bdActor);
	        return new ActorDTO(bdActor);
	        }
	 
	 @PreAuthorize("hasAuthority('ADMIN')")
	    @Transactional(readOnly = false)
	    @Override
	    public String deleteById(Long idActor) {
		 	String resultado;
			Actor bdActor = actorDAO.findById(idActor);
			if (bdActor.getActuations().size()==0){
				actorDAO.deleteById(idActor);
				resultado="exito";
				return resultado;
			}
			else {
				resultado="fracaso";
				return resultado;
			}
	    }
	
}
