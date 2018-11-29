package es.udc.lbd.asi.restexample.model.service.dto;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import es.udc.lbd.asi.restexample.model.domain.NormalUser;
import es.udc.lbd.asi.restexample.model.domain.UserAuthority;
import es.udc.lbd.asi.restexample.model.domain.UserNoti;
import es.udc.lbd.asi.restexample.model.domain.User_;

public class NormalUserListUserDTO {

	
	@NotEmpty
	private String login;
	@NotEmpty
	private String email;
	@NotEmpty
	private Long countVista;
	@NotEmpty
	private Long countPendiente;
	@NotEmpty
	private Long countValoration;
	@NotNull
	private Date data;
	private UserNoti noti;
	
	
	
	
	public NormalUserListUserDTO() {
	}
	
	public NormalUserListUserDTO(NormalUser user) {
		this.login = user.getLogin();
		this.email = user.getEmail();
		this.countPendiente = new Long(0);
		this.countVista = new Long(0);
		this.countValoration = new Long(0);
		this.data=user.getData();
		this.noti=user.getNotification();
		
	}

	

	public UserNoti getNoti() {
		return noti;
	}

	public void setNoti(UserNoti noti) {
		this.noti = noti;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	
}
