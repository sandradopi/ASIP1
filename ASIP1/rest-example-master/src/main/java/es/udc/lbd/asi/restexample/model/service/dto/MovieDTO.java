package es.udc.lbd.asi.restexample.model.service.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import es.udc.lbd.asi.restexample.model.domain.Movie;


public class MovieDTO {
    private Long idMovie;
    @NotEmpty
    private String name;
    private String summary;
    @NotNull
    private Integer duration;
    @NotNull
    private Date data ;

    public MovieDTO() {
    	
    }
    
    public MovieDTO(Movie movie) {
    	 this.idMovie = movie.getIdMovie();
         this.name = movie.getName();
         this.summary = movie.getSummary();
         this.duration = movie.getDuration();
         this.data = movie.getData();
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
    }

    