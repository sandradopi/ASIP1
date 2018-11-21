package es.udc.lbd.asi.restexample.model.service.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

import es.udc.lbd.asi.restexample.model.domain.UserAuthority;
import es.udc.lbd.asi.restexample.model.domain.User_;

public class NormalUserListUserDTO {

	
	@NotEmpty
	private String login;
	@NotEmpty
	private String email;
	private Long countVista;
	private Long countPendiente;
	private Long countValoration;
	
	
	
	
	public NormalUserListUserDTO() {
	}
	
	public NormalUserListUserDTO(User_ user) {
		this.login = user.getLogin();
		this.email = user.getEmail();
		this.countPendiente = new Long(0);
		this.countVista = new Long(0);
		this.countValoration = new Long(0);
	}

	

	public Long getCountVista() {
		return countVista;
	}

	public void setCountVista(Long long1) {
		this.countVista = long1;
	}

	public Long getCountPendiente() {
		return countPendiente;
	}

	public void setCountPendiente(Long countPendiente) {
		this.countPendiente = countPendiente;
	}

	public Long getCountValoration() {
		return countValoration;
	}

	public void setCountValoration(Long countValoration) {
		this.countValoration = countValoration;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
