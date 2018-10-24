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

	
	public NormalUser() {
    }


	public NormalUser(String login, String email, String password, String name, String surname1, String surname2,
			int phone) {
		super(login, email, password, name, surname1, surname2, phone);

	}

	

}
