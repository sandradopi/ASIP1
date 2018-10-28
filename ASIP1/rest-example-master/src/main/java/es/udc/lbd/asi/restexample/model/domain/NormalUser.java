package es.udc.lbd.asi.restexample.model.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name="idUser")
@Table(name="NormalUser")
public class NormalUser extends User_ {

	public NormalUser() {
		super();
	}

	public NormalUser(String login, String email, String password, UserAuthority authority) {
		super(login, email, password, authority);
	}
	

}
