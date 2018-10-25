package es.udc.lbd.asi.restexample.model.domain;

import java.util.Date;
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
@Table(name="Actor")

public class Actor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idActor")
	private Long idActor;
	@NotEmpty
	private String name;
	@NotEmpty
	private String surname1;
	private String surname2;
	private Date birthdate;
	
	@ManyToMany(mappedBy = "participantes", fetch = FetchType.LAZY)
    private Set<Movie> actuations = new HashSet<Movie>();

	public Actor() {
    }

	public Actor(@NotEmpty String name, @NotEmpty String surname1, String surname2, Date birthdate,
			Set<Movie> actuations) {
		super();
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
		this.birthdate = birthdate;
		this.actuations = actuations;
	}
	
	public Actor(@NotEmpty String name, @NotEmpty String surname1, String surname2, Date birthdate) {
		super();
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
		this.birthdate = birthdate;
	}

	public Actor(@NotEmpty String name, @NotEmpty String surname1) {
		super();
		this.name = name;
		this.surname1 = surname1;
	}
	
	public Actor(@NotEmpty String name, @NotEmpty String surname1, Date birthdate) {
		super();
		this.name = name;
		this.surname1 = surname1;
		this.birthdate = birthdate;
	}
	
	public Actor(@NotEmpty String name, @NotEmpty String surname1, @NotEmpty String surname2) {
		super();
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
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
