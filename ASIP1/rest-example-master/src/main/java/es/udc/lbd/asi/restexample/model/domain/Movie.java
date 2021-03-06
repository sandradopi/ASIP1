package es.udc.lbd.asi.restexample.model.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Movie")

public class Movie {
   

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovie;
	private Boolean hidden;
	@NotEmpty
    private String name;
    private String summary;
    private String url;
    @NotNull
    private Integer duration;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date data;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "idGenre")
    @NotNull
    private Genre genre;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name = "MOVIE_ACTOR",
    		joinColumns = {@JoinColumn(name = "idMovie")},
    		inverseJoinColumns = {@JoinColumn(name = "idActor")}
    		)
    private Set<Actor> participantes = new HashSet<Actor>();
    
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name = "MOVIE_DIRECTOR",
    		joinColumns = {@JoinColumn(name = "idMovie")},
    		inverseJoinColumns = {@JoinColumn(name = "idDirector")}
    		)
    @NotEmpty
    private Set<Director> dirigentes = new HashSet<Director>();
    
    public Movie() {
    }


	public Movie(Boolean hidden, @NotEmpty String name, String summary, @NotNull Integer duration, @NotNull Date data,
			@NotNull Genre genre, Set<Actor> participantes, @NotNull Set<Director> dirigentes, String url) {
		super();
		this.hidden = hidden;
		this.name = name;
		this.summary = summary;
		this.duration = duration;
		this.data = data;
		this.genre = genre;
		this.participantes = participantes;
		this.dirigentes = dirigentes;
		this.url= url;
	}
	
	public Movie(Boolean hidden, @NotEmpty String name, String summary, @NotNull Integer duration, @NotNull Date data) {
		super();
		this.hidden = hidden;
		this.name = name;
		this.summary = summary;
		this.duration = duration;
		this.data = data;
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

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
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


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}

	
	

}