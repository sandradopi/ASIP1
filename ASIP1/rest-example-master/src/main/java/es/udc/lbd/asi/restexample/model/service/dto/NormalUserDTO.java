package es.udc.lbd.asi.restexample.model.service.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

import es.udc.lbd.asi.restexample.model.domain.User_;
import es.udc.lbd.asi.restexample.model.domain.UserAuthority;
import es.udc.lbd.asi.restexample.model.domain.UserNoti;

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
	@NotEmpty
	private UserNoti noti;
	
	
	public NormalUserDTO() {
	}
	
	public NormalUserDTO(User_ user) {
		this.idUser = user.getIdUser();
		this.login = user.getLogin();
		this.email=user.getEmail();
		this.authority= UserAuthority.USER;
		this.noti= user.getNotification();
	}

	
	
	public UserAuthority getAuthority() {
		return authority;
	}

	public void setAuthority(UserAuthority authority) {
		this.authority = authority;
	}

	public UserNoti getNoti() {
		return noti;
	}

	public void setNoti(UserNoti noti) {
		this.noti = noti;
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
