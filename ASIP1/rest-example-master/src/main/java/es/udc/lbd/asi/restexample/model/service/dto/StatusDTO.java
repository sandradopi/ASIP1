package es.udc.lbd.asi.restexample.model.service.dto;


import javax.validation.constraints.NotNull;
import es.udc.lbd.asi.restexample.model.domain.Status;
import es.udc.lbd.asi.restexample.model.domain.TipoStatus;

public class StatusDTO {

	private Long idStatus;
	private Integer valoration;
	private TipoStatus type;
	@NotNull
	private MovieDTO movie;
	@NotNull
	 private NormalUserDTO normalUser;
	
	public StatusDTO() {
		
	}
	
	public StatusDTO(Status status) {
		this.idStatus = status.getIdStatus();
		this.valoration = status.getValoration();
		this.type=status.getType();
		this.movie = new MovieDTO(status.getMovie());
		this.normalUser = new NormalUserDTO(status.getNormalUser());
		
	}

	public Long getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
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

	public MovieDTO getMovie() {
		return movie;
	}

	public void setMovie(MovieDTO movie) {
		this.movie = movie;
	}

	public NormalUserDTO getNormalUser() {
		return normalUser;
	}

	public void setNormalUser(NormalUserDTO normalUser) {
		this.normalUser = normalUser;
	}
	
	
	
}
