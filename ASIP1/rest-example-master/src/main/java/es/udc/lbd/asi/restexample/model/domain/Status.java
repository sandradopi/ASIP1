package es.udc.lbd.asi.restexample.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Status")

public class Status {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idStatus;
	private int valoration;
	private tipoStatus type;
	
	@ManyToOne
	@JoinColumn (name = "idMovie")
	@NotNull
	private Movie movie;
	
	@ManyToOne
	@JoinColumn (name = "idUser")
	@NotNull
	private User_ user;

	public Status() {
		
	}
	
	public Status(int valoration, tipoStatus type, Movie movie, User_ user) {
		super();
		this.valoration = valoration;
		this.type = type;
		this.movie = movie;
		this.user = user;
	}
	
	public Status(int valoration, tipoStatus type) {
		super();
		this.valoration = valoration;
		this.type = type;
	}

	public int getValoration() {
		return valoration;
	}

	public void setValoration(int valoration) {
		this.valoration = valoration;
	}

	public tipoStatus getType() {
		return type;
	}

	public void setType(tipoStatus type) {
		this.type = type;
	}

	public Long getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}
	
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	public User_ getNormalUser() {
		return user;
	}

	public void setNormalUser(User_ user) {
		this.user = user;
	}

}
