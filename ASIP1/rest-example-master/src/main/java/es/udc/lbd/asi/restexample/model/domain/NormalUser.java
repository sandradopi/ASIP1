package es.udc.lbd.asi.restexample.model.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name="idUser")
@Table(name="NormalUser")
public class NormalUser extends User_ {
	
	@Enumerated(EnumType.STRING)
    private UserNoti noti;

	public NormalUser() {
		super();
	}

	public NormalUser(String login, String email, String password, UserAuthority authority, Date data, UserNoti noti) {
		super(login, email, password, authority, data);
		this.noti=noti;
		
	}

	public UserNoti getNotification() {
		return noti;
	}

	public void setNotification(UserNoti noti) {
		this.noti = noti;
	}

	


	

	

}
