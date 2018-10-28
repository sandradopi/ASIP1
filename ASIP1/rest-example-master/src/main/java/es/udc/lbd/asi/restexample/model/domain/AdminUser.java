package es.udc.lbd.asi.restexample.model.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name="idUser")
@Table(name="AdminUser")
public class AdminUser extends User_{

	public AdminUser() {
		super();
	}

	public AdminUser(String login, String email, String password, UserAuthority authority) {
		super(login, email, password, authority);
	}

	
}
