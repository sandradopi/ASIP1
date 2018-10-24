package es.udc.lbd.asi.restexample.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Genre")

public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGenre;
	@NotEmpty
	private String type;
	
	public Genre() {
		
	}
	
	public Genre(String type) {
		super();
		this.type = type;
	}
	
	public Long getIdGenre() {
		return idGenre;
	}

	public void setIdGenre(Long idGenre) {
		this.idGenre = idGenre;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
		
}
