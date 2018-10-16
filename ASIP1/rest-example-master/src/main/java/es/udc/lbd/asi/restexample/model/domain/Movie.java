package es.udc.lbd.asi.restexample.model.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Movie")

public class Movie {
    @Id
    @Column(name="idMovie")
    private Long id;
    private String name;
    private String summary;
    private Integer duration;
    private Date data;

    public Movie() {
    }

	public Movie(Long id, String name, String summary, Integer duration, Date data) {
		super();
		this.id = id;
		this.name = name;
		this.summary = summary;
		this.duration = duration;
		this.data = data;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

    

  
}
