package es.udc.lbd.asi.restexample.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	private Movie movie;
	
	@ManyToOne
	@JoinColumn (name = "idUser")
	private NormalUser normalUser;

	
	public Status() {
		
	}
	
	
	public Status(int valoration, tipoStatus type, Movie movie, NormalUser normalUser) {
		super();
		this.valoration = valoration;
		this.type = type;
		this.movie = movie;
		this.normalUser = normalUser;
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
	
	public NormalUser getNormalUser() {
		return normalUser;
	}

	public void setNormalUser(NormalUser normalUser) {
		this.normalUser = normalUser;
	}


}
