package es.udc.lbd.asi.restexample.model.service.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Movie;

public class ActorDTO {

	private Long idActor;
	private String name;
	private String surname1;
	private String surname2;
	private Date birthdate;
	private Set<Movie> actuations = new HashSet<Movie>();
	
	public ActorDTO() {
		
	}
	
	public ActorDTO(Actor actor) {
		this.idActor = actor.getIdActor();
		this.name = actor.getName();
		this.surname1 = actor.getSurname1();
		this.surname2 = actor.getSurname2();
		this.birthdate = actor.getBirthdate();
		this.actuations = actor.getActuations();
	}
	
	public Long getIdActor() {
		return idActor;
	}
	
	public void setIdActor(Long idActor) {
		this.idActor = idActor;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname1() {
		return surname1;
	}
	
	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}
	
	public String getSurname2() {
		return surname2;
	}
	
	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}
	
	public Date getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	public Set<Movie> getActuations() {
		return actuations;
	}
	
	public void setActuations(Set<Movie> actuations) {
		this.actuations = actuations;
	}
	
}
