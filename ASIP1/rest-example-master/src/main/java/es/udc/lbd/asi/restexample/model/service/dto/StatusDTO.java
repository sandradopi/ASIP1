package es.udc.lbd.asi.restexample.model.service.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.NormalUser;
import es.udc.lbd.asi.restexample.model.domain.Status;
import es.udc.lbd.asi.restexample.model.domain.tipoStatus;

public class StatusDTO {

	private Long idStatus;
	private int valoration;
	private tipoStatus type;
	@NotNull
	private MovieDTO movie;
	@NotNull
	//private NormalUser normalUser;
	
	public StatusDTO() {
		
	}
	
	public StatusDTO(Status status) {
		this.idStatus = status.getIdStatus();
		this.valoration = status.getValoration();
		this.movie = new MovieDTO(status.getMovie());
		//this.normalUser = new NormalUserDTO(status.getNormalUser());
		
	}

	public Long getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
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

	public MovieDTO getMovie() {
		return movie;
	}

	public void setMovie(MovieDTO movie) {
		this.movie = movie;
	}
	
	
	
}
