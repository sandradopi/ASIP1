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

	
	@OneToMany
    @JoinColumn (name = "idStatus")
	private Set<Status> status = new HashSet<Status>();
	
	public NormalUser() {
    }


	public NormalUser( String login, String password, String name, String surname1, String surname2, int phone, Set<Status> status) {
		super(login, password, name, surname1, surname2, phone);
		this.status=status;
	}


	

	public Set<Status> getStatus() {
		return status;
	}


	public void setStatus(Set<Status> status) {
		this.status = status;
	}

	

}
