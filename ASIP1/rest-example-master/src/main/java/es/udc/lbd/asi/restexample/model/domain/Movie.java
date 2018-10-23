package es.udc.lbd.asi.restexample.model.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Movie")

public class Movie {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovie;
	@NotEmpty
    private String name;
    private String summary;
    @NotNull
    private Integer duration;
    //@Type NO FUNCIONA, MIRARLO
    @NotNull
    private Date data;

    @ManyToOne
    @JoinColumn (name = "idGenre")
    private Genre genre;
    
    @OneToMany
    @JoinColumn (name = "idStatus")
    private Set<Status> status = new HashSet<Status>();
    
    
    @ManyToMany
    @JoinTable(
    		name = "MOVIE_ACTOR",
    		joinColumns = {@JoinColumn(name = "idMovie")},
    		inverseJoinColumns = {@JoinColumn(name = "idActor")}
    		)
    private Set<Actor> participantes = new HashSet<Actor>();
    
    @ManyToMany
    @JoinTable(
    		name = "MOVIE_DIRECTOR",
    		joinColumns = {@JoinColumn(name = "idMovie")},
    		inverseJoinColumns = {@JoinColumn(name = "idDirector")}
    		)
    private Set<Director> dirigentes = new HashSet<Director>();
    
    public Movie() {
    }

	public Movie(String name, String summary, Integer duration, Date data) {
		super();
		this.name = name;
		this.summary = summary;
		this.duration = duration;
		this.data = data;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((idMovie == null) ? 0 : idMovie.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (idMovie == null) {
			if (other.idMovie != null)
				return false;
		} else if (!idMovie.equals(other.idMovie))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
			return false;
		return true;
	}

	@Override
	public String toString() {		return "Movie [id=" + idMovie + ", name=" + name + ", summary=" + summary + ", duration=" + duration + ", data="
				+ data + "]";
	}

}
