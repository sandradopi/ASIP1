package es.udc.lbd.asi.restexample.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Genre")

public class Genre {

	private Long idGenre;
	private String type;
	
	public Genre() {
		
	}
	
	public Genre(Long idGenre, String type) {
		super();
		this.idGenre = idGenre;
		this.type = type;
	}
	
	@Id
	@SequenceGenerator (name="idGenre", sequenceName ="id_genre_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "idGenre")
	@Column(name="idGenre", nullable=false)
	public Long getIdGenre() {
		return idGenre;
	}

	public void setIdGenre(Long idGenre) {
		this.idGenre = idGenre;
	}

	@Column(name="type", nullable=false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idGenre == null) ? 0 : idGenre.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Genre other = (Genre) obj;
		if (idGenre == null) {
			if (other.idGenre != null)
				return false;
		} else if (!idGenre.equals(other.idGenre))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Genre [id=" + idGenre + ", type=" + type + "]";
	}
	
}
