package es.udc.lbd.asi.restexample.model.service.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.udc.lbd.asi.restexample.model.domain.User_;
import es.udc.lbd.asi.restexample.model.domain.UserAuthority;

public class NormalUserDTO {
	private Long idUser;
	@NotEmpty
	private String login;
	@NotEmpty
	private String email;
	@NotEmpty
	private String password;
	@Enumerated(EnumType.STRING)
	private UserAuthority authority;
	
	
	public NormalUserDTO() {
	}
	
	public NormalUserDTO(User_ user) {
		this.idUser = user.getIdUser();
		this.login = user.getLogin();
		this.email=user.getEmail();
		this.authority= UserAuthority.USER;
	}

	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
