package es.udc.lbd.asi.restexample.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Table(name="User_")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)


public class User_ {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idUser")
	private Long idUser;
	
	@Column(unique = true)
	@NotEmpty
	private String login;
	
	@Column(unique = true)
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String password;
	
	@Enumerated(EnumType.STRING)
    private UserAuthority authority;
	
	public User_() {
		
    }
	

	public User_(String login,String email, String password,  UserAuthority authority) {
		this.login = login;
		this.email=email;
		this.password = password;
		this.authority=authority;
	}
	
	
	
	public UserAuthority getAuthority() {
		return authority;
	}


	public void setAuthority(UserAuthority authority) {
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
