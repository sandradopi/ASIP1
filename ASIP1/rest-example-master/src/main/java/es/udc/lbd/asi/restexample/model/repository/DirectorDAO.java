package es.udc.lbd.asi.restexample.model.repository;

import es.udc.lbd.asi.restexample.model.domain.Director;

public interface DirectorDAO {
	public void save(Director director);
	 public Director findById(Long id);
}
