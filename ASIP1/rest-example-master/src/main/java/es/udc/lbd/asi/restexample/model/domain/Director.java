package es.udc.lbd.asi.restexample.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Director")

public class Director {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDirector")
	private Long idDirector;
	@NotEmpty
	private String name;
	@NotEmpty
	private String surname1;
	private String surname2;
	
	public Director() {
	}
	
	public Director(String name, String surname1, String surname2) {
		super();
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
	}
	
	public Long getIdDirector() {
		return idDirector;
	}
	
	public void setIdDirector(Long idDirector) {
		this.idDirector = idDirector;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname1() {
		return surname1;
	}
	
	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}
	
	public String getSurname2() {
		return surname2;
	}
	
	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idDirector == null) ? 0 : idDirector.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname1 == null) ? 0 : surname1.hashCode());
		result = prime * result + ((surname2 == null) ? 0 : surname2.hashCode());
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
		Director other = (Director) obj;
		if (idDirector == null) {
			if (other.idDirector != null)
				return false;
		} else if (!idDirector.equals(other.idDirector))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname1 == null) {
			if (other.surname1 != null)
				return false;
		} else if (!surname1.equals(other.surname1))
			return false;
		if (surname2 == null) {
			if (other.surname2 != null)
				return false;
		} else if (!surname2.equals(other.surname2))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Director [id=" + idDirector + ", name=" + name + ", surname1=" + surname1 + ", surname2=" + surname2 + "]";
	}
}
