package es.udc.lbd.asi.restexample.model.service.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Director;
import es.udc.lbd.asi.restexample.model.domain.Genre;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.Status;


public class MovieDTO {
    private Long idMovie;
    @NotEmpty
    private String name;
    private String summary;
    @NotNull
    private Integer duration;
    @NotNull
    private Date data ;
    @NotNull
    private Genre genre;
    private Set<Status> status = new HashSet<Status>();
    private Set<Actor> participantes = new HashSet<Actor>();
    @NotNull
    private Set<Director> dirigentes = new HashSet<Director>();

    public MovieDTO() {
    	
    }
    
    public MovieDTO(Movie movie) {
    	 this.idMovie = movie.getIdMovie();
         this.name = movie.getName();
         this.summary = movie.getSummary();
         this.duration = movie.getDuration();
         this.data = movie.getData();
         this.genre = movie.getGenre();
         this.status = movie.getStatus();
         this.participantes = movie.getParticipantes();
         this.dirigentes = movie.getDirigentes();
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

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Set<Status> getStatus() {
		return status;
	}

	public void setStatus(Set<Status> status) {
		this.status = status;
	}

	public Set<Actor> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(Set<Actor> participantes) {
		this.participantes = participantes;
	}

	public Set<Director> getDirigentes() {
		return dirigentes;
	}

	public void setDirigentes(Set<Director> dirigentes) {
		this.dirigentes = dirigentes;
	}
	
    }

    