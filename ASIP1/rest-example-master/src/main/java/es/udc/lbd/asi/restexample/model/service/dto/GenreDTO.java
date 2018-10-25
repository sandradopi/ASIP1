package es.udc.lbd.asi.restexample.model.service.dto;

import es.udc.lbd.asi.restexample.model.domain.Genre;

public class GenreDTO {

	private Long idGenre;
	private String type;
	
	public GenreDTO() {
		
	}
	
	public GenreDTO(Genre genre) {
		this.idGenre = genre.getIdGenre();
		this.type = genre.getType();
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
