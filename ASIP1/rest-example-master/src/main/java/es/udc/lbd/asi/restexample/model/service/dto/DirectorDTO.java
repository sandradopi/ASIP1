package es.udc.lbd.asi.restexample.model.service.dto;

import java.util.HashSet;
import java.util.Set;

import es.udc.lbd.asi.restexample.model.domain.Director;
import es.udc.lbd.asi.restexample.model.domain.Movie;

public class DirectorDTO {
	private Long idDirector;
	private String name;
	private String surname1;
	private String surname2;
	private Set<Movie> managment = new HashSet<Movie>();
	
	public DirectorDTO() {
		
	}
	
	public DirectorDTO(Director director) { 
		this.idDirector = director.getIdDirector();
		this.name = director.getName();
		this.surname1 = director.getSurname1();
		this.surname2 = director.getSurname2();
		this.managment = director.getManagment();
	}

	public Long getIdDirector() {
		return idDirector;
	}

	public void setIdDirector(Long idDirector) {
		this.idDirector = idDirector;
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

	public Set<Movie> getManagment() {
		return managment;
	}

	public void setManagment(Set<Movie> managment) {
		this.managment = managment;
	}
	
}
