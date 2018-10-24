package es.udc.lbd.asi.restexample.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name="idUser")
@Table(name="AdminUser")

public class AdminUser extends User {

	
	public AdminUser() {
    }
	


	public AdminUser(String login, String password, String name, String surname1, String surname2, int phone) {
		super(login, password, name, surname1, surname2, phone);
	}



}
