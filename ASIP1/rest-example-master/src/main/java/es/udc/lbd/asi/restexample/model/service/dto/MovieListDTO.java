package es.udc.lbd.asi.restexample.model.service.dto;

import javax.validation.constraints.NotEmpty;

import es.udc.lbd.asi.restexample.model.domain.Movie;

public class MovieListDTO {
	 private Long idMovie;
	 @NotEmpty
	 private String name;
	 @NotEmpty
	 private Long media;
	 
	 public MovieListDTO() {
			
		}
		 
	public MovieListDTO(Movie movie) {
		this.idMovie = movie.getIdMovie();
		this.name = movie.getName();
		this.media = new Long(0);
	}

	public Long getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(Long idMovie) {
		this.idMovie = idMovie;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMedia() {
		return media;
	}

	public void setMedia(Long media) {
		this.media = media;
	}
	 
	 
	 
	 
	 
}