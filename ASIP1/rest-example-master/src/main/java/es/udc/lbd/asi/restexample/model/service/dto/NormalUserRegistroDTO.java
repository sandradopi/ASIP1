package es.udc.lbd.asi.restexample.model.service.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

import es.udc.lbd.asi.restexample.model.domain.User_;
import es.udc.lbd.asi.restexample.model.domain.UserAuthority;
import es.udc.lbd.asi.restexample.model.domain.UserNoti;

public class NormalUserRegistroDTO {
	
	@NotEmpty
	private String login;
	
	@NotEmpty
	private String email;
	

	
	public NormalUserRegistroDTO() {
	}
	
	
	public NormalUserRegistroDTO(User_ user) {
		this.login = user.getLogin();
		this.email=user.getEmail();
	
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	
	

}
