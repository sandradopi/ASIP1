package es.udc.lbd.asi.restexample.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="StatusPending")
@PrimaryKeyJoinColumn(name="idStatus")

public class StatusPending extends Status{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idStatus;
	
	public StatusPending() {
		super();
	}

	public Long getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idStatus == null) ? 0 : idStatus.hashCode());
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
		StatusPending other = (StatusPending) obj;
		if (idStatus == null) {
			if (other.idStatus != null)
				return false;
		} else if (!idStatus.equals(other.idStatus))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Genre [id=" + idStatus + "]";
	}


}
