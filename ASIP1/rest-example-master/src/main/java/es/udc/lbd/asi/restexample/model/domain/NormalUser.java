package es.udc.lbd.asi.restexample.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name="idUser")
@Table(name="NormalUser")

public class NormalUser extends User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idUser")
	private Long idUser;
	
	@OneToMany
    @JoinColumn (name = "idStatus")
	private Set<Status> status = new HashSet<Status>();
	
	public NormalUser() {
    }


	public NormalUser( String login, String password, String name, String surname1, String surname2, int phone, Set<Status> status) {
		super(login, password, name, surname1, surname2, phone);
		this.status=status;
	}


	public Long getIdUser() {
		return idUser;
	}


	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
	

	public Set<Status> getStatus() {
		return status;
	}


	public void setStatus(Set<Status> status) {
		this.status = status;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		NormalUser other = (NormalUser) obj;
		if (idUser == null) {
			if (other.idUser != null)
				return false;
		} else if (!idUser.equals(other.idUser))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "NormalUser [idUser=" + idUser + "]";
	}

	

}
