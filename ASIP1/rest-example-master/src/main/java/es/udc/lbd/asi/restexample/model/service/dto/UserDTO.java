package es.udc.lbd.asi.restexample.model.service.dto;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.udc.lbd.asi.restexample.model.domain.User;

public class UserDTO {
	private Long idUser;
	@NotEmpty
	private String login;
	@NotEmpty
	private String password;
	@NotEmpty
	private String name;
	@NotEmpty
	private String surname1;
	private String surname2;
	private int phone;
	
	
	public UserDTO() {
	}
	
	public UserDTO(User user) {
		this.idUser = user.getIdUser();
		this.login = user.getLogin();
		this.password = user.getPassword();
		this.name = user.getName();
		this.surname1 = user.getSurname1();
		this.surname2 = user.getSurname2();
		this.phone = user.getPhone();
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
