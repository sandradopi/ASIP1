package es.udc.lbd.asi.restexample.model.repository;

import es.udc.lbd.asi.restexample.model.domain.Status;

public interface StatusDAO {
	public void save(Status status);
	 public Status findById(Long id);
}
