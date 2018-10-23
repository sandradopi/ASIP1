package es.udc.lbd.asi.restexample.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="StatusViewed")
@PrimaryKeyJoinColumn(name="idStatus")

public class StatusViewed extends Status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idStatus;
	@Column(unique= false)
	private int valoration;
	
	public StatusViewed() {
		
	}
	
	public StatusViewed(int valoration) {
		super();
		this.valoration = valoration;
	}

	public Long getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}

	public int getValoration() {
		return valoration;
	}

	public void setValoration(int valoration) {
		this.valoration = valoration;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idStatus == null) ? 0 : idStatus.hashCode());
		result = prime * result + valoration;
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
		StatusViewed other = (StatusViewed) obj;
		if (idStatus == null) {
			if (other.idStatus != null)
				return false;
		} else if (!idStatus.equals(other.idStatus))
			return false;
		if (valoration != other.valoration)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Genre [id=" + idStatus + ", valoration=" + valoration + "]";
	}
}
