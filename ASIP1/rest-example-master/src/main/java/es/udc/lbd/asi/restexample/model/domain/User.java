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

	private Long id;
	private String login;
	private String password;
	private String name;
	private String surname1;
	private String surname2;
	private int phone;
	
	public User() {
    }

	public User(Long id, String login, String password, String name, String surname1, String surname2, int phone) {
		super();
		this.id= id;
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
		this.phone = phone;
	}
	
	@Id
	@SequenceGenerator (name="id", sequenceName ="id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + phone;
		result = prime * result + ((surname1 == null) ? 0 : surname1.hashCode());
		result = prime * result + ((surname2 == null) ? 0 : surname2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone != other.phone)
			return false;
		if (surname1 == null) {
			if (other.surname1 != null)
				return false;
		} else if (!surname1.equals(other.surname1))
			return false;
		if (surname2 == null) {
			if (other.surname2 != null)
				return false;
		} else if (!surname2.equals(other.surname2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", name=" + name + ", surname1="
				+ surname1 + ", surname2=" + surname2 + ", phone=" + phone + "]";
	}
	
	
}
