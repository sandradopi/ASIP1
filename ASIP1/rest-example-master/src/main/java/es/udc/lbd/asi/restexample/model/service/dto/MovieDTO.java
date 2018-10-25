package es.udc.lbd.asi.restexample.model.service.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Director;
import es.udc.lbd.asi.restexample.model.domain.Genre;
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
    private Set<ActorDTO> participantes = new HashSet<ActorDTO>();
    @NotNull
    private Set<DirectorDTO> dirigentes = new HashSet<DirectorDTO>();
    private Set<ActorDTO> auxiliarA = new HashSet<ActorDTO>();
    
    
    private Set<DirectorDTO> auxiliarD = new HashSet<DirectorDTO>();
    
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
         for(Actor a: movie.getParticipantes()){
        	 auxiliarA.add(new ActorDTO(a));
         }
         for(Director d: movie.getDirigentes()){
        	 auxiliarD.add(new DirectorDTO(d));
         }
         this.participantes=auxiliarA;
         this.dirigentes=auxiliarD;
         
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


	public Set<ActorDTO> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(Set<ActorDTO> participantes) {
		this.participantes = participantes;
	}

	public Set<DirectorDTO> getDirigentes() {
		return dirigentes;
	}

	public void setDirigentes(Set<DirectorDTO> dirigentes) {
		this.dirigentes = dirigentes;
	}
	
    }

    