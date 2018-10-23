package es.udc.lbd.asi.restexample.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.JOINED) //Probar con Signgle table si no funciona solo una clase
@Table(name="Status")

public class Status {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idStatus;
	
	@ManyToOne
	@JoinColumn (name = "idMovie")
	private Movie movie;
	
	@ManyToOne
	@JoinColumn (name = "idUser")
	private NormalUser normalUser;

	public Status() {
		
	}

	public Long getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}
	
	/*public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	public NormalUser getNormalUser() {
		return normalUser;
	}

	public void setNormalUser(NormalUser normalUser) {
		this.normalUser = normalUser;
	}*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idStatus == null) ? 0 : idStatus.hashCode());
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
		Status other = (Status) obj;
		if (idStatus == null) {
			if (other.idStatus != null)
				return false;
		} else if (!idStatus.equals(other.idStatus))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Genre [id=" + idStatus + "]";
	}

}
