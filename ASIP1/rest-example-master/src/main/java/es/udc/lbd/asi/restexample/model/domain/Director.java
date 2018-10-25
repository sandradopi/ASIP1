package es.udc.lbd.asi.restexample.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Director")

public class Director {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDirector")
	private Long idDirector;
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String surname1;
	private String surname2;
	
	@ManyToMany(mappedBy="dirigentes",fetch = FetchType.LAZY)
	private Set<Movie> managment = new HashSet<Movie>();
	
	public Director() {
	}
	
	public Director(@NotEmpty String name, @NotEmpty String surname1, String surname2 , Set<Movie> managment) {
		super();
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
		this.managment = managment;
	}
	
	public Director(@NotEmpty String name, @NotEmpty String surname1, String surname2) {
		super();
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
	}

	public Director(@NotEmpty String name, @NotEmpty String surname1) {
		this.name = name;
		this.surname1 = surname1;
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
