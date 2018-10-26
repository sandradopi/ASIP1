package es.udc.lbd.asi.restexample.model.service.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import es.udc.lbd.asi.restexample.model.domain.Actor;

public class ActorDTO {

	private Long idActor;
	@NotEmpty
	private String name;
	@NotEmpty
	private String surname1;
	private String surname2;
	private Date birthdate;
	
	public ActorDTO() {
		
	}
	
	public ActorDTO(Actor actor) {
		this.idActor = actor.getIdActor();
		this.name = actor.getName();
		this.surname1 = actor.getSurname1();
		this.surname2 = actor.getSurname2();
		this.birthdate = actor.getBirthdate();
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
	
}
