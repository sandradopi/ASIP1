package es.udc.lbd.asi.restexample.model.service.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import es.udc.lbd.asi.restexample.model.domain.Movie;


public class MovieDTO {
    private Long idMovie;
    private Boolean hidden;
    @NotEmpty
    private String name;
    private String summary;
    @NotNull
    private Integer duration;
    @NotNull
    private Date data ;
    @NotNull
    private GenreDTO genre;
    
    public MovieDTO() {
    	
    }
    
    public MovieDTO(Movie movie) {
    	 this.idMovie = movie.getIdMovie();
    	 this.hidden=movie.getHidden();
         this.name = movie.getName();
         this.summary = movie.getSummary();
         this.duration = movie.getDuration();
         this.data = movie.getData();
         this.genre = new GenreDTO(movie.getGenre());
    }
    
	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public GenreDTO getGenre() {
		return genre;
	}

	public void setGenre(GenreDTO genre) {
		this.genre = genre;
	}
	
    }

    