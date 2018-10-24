package es.udc.lbd.asi.restexample.model.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name="idUser")
@Table(name="AdminUser")

public class AdminUser extends User {
	
	public AdminUser() {
		
    }

	public AdminUser(String login, String email, String password, String name, String surname1, String surname2,
			int phone) {
		super(login, email, password, name, surname1, surname2, phone);
	}
	
}
