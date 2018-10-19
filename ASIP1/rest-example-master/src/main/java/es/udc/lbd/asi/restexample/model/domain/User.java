package es.udc.lbd.asi.restexample.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="User")

public class User {

	private String login;
	private String password;
	private String name;
	private String surname1;
	private String surname2;
	private int phone;
	
	public User() {
    }

	public User(String login, String password, String name, String surname1, String surname2, int phone) {
		super();
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
		this.phone = phone;
	}
	
	@Id
	@SequenceGenerator (name="login", sequenceName ="login_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "login")
	@Column(name="login", nullable=false)
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	@Column(name="password", nullable=false)
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="name", nullable=false)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="surname1", nullable=false)
	public String getSurname1() {
		return surname1;
	}
	
	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}
	
	@Column(name="surname2")
	public String getSurname2() {
		return surname2;
	}
	
	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}
	
	@Column(name="phone")
	public int getPhone() {
		return phone;
	}
	
	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return "User [login=" + login + ", password=" + password + ", name=" + name + ", surname1=" + surname1 + ", surname2=" + surname2 + ", phone=" + phone + "]";
	}
}
