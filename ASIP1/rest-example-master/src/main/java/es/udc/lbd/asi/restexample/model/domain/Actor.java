package es.udc.lbd.asi.restexample.model.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Actor")

public class Actor {
	
	private Long idActor;
	private String name;
	private String surname1;
	private String surname2;
	private Date birthdate;
	
	public Actor() {
    }

	public Actor(Long idActor, String name, String surname1, String surname2, Date birthdate) {
		super();
		this.idActor = idActor;
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
		this.birthdate = birthdate;
	}
	
	@Id
	@SequenceGenerator (name="idActor", sequenceName ="id_actor_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "idActor")
	@Column(name="idActor", nullable=false)
	public Long getIdActor() {
		return idActor;
	}
	
	public void setIdActor(Long idActor) {
		this.idActor = idActor;
	}
	
	@Column(name="name", nullable=false)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="surname1", nullable=false)
	public String getSurname1() {
		return surname1;
	}
	
	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}
	
	@Column(name="surname2")
	public String getSurname2() {
		return surname2;
	}
	
	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}
	
	@Column(name="birthdate", nullable=false)
	public Date getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthdate == null) ? 0 : birthdate.hashCode());
		result = prime * result + ((idActor == null) ? 0 : idActor.hashCode());
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
		Actor other = (Actor) obj;
		if (birthdate == null) {
			if (other.birthdate != null)
				return false;
		} else if (!birthdate.equals(other.birthdate))
			return false;
		if (idActor == null) {
			if (other.idActor != null)
				return false;
		} else if (!idActor.equals(other.idActor))
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
		return "Actor [id=" + idActor + ", name=" + name + ", surname1=" + surname1 + ", surname2=" + surname2 + ", birthdate=" + birthdate + "]";
	}

}
