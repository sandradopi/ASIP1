package es.udc.lbd.asi.restexample.model.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Status")

public class Status {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idStatus;
	private Integer valoration;
	@Enumerated(EnumType.STRING)
	private TipoStatus type;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@ManyToOne
	@JoinColumn (name = "idMovie")
	@NotNull
	private Movie movie;
	
	@ManyToOne
	@JoinColumn (name = "idUser")
	@NotNull
	private NormalUser user;

	public Status() {
		
	}
	
	public Status(Integer valoration, TipoStatus type, Movie movie, NormalUser user, Date data) {
		super();
		this.valoration = valoration;
		this.type = type;
		this.movie = movie;
		this.user = user;
		this.data=data;
	}
	
	
	

	public Status(Integer valoration, TipoStatus type) {
		super();
		this.valoration = valoration;
		this.type = type;
	}

	
	public Integer getValoration() {
		return valoration;
	}

	public void setValoration(int valoration) {
		this.valoration = valoration;
	}

	public TipoStatus getType() {
		return type;
	}

	public void setType(TipoStatus type) {
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
		return user;
	}

	public void setNormalUser(NormalUser user) {
		this.user = user;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	

	
	

}
