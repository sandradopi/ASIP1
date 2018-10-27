package es.udc.lbd.asi.restexample.model.service.dto;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.udc.lbd.asi.restexample.model.domain.NormalUser;
import es.udc.lbd.asi.restexample.model.domain.User;

public class AdminUserDTO {
	private Long idUser;
	@NotEmpty
	private String login;
	@NotEmpty
	private String email;
	@NotEmpty
	private String password;
	@NotEmpty
	private String name;
	@NotEmpty
	private String surname1;
	private String surname2;
	private int phone;
	private String authority;
	
	
	public AdminUserDTO() {
	}
	
	public AdminUserDTO(User user) {
		this.idUser = user.getIdUser();
		this.login = user.getLogin();
		this.email=user.getEmail();
		this.password = user.getPassword();
		this.name = user.getName();
		this.surname1 = user.getSurname1();
		this.surname2 = user.getSurname2();
		this.phone = user.getPhone();
		this.authority = user.getAuthority().name();
	}

	public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
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

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname1() {
		return surname1;
	}

	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}

	public String getSurname2() {
		return surname2;
	}

	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}
	
}
