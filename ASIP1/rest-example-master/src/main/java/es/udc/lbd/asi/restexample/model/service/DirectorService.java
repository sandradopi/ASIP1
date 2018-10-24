package es.udc.lbd.asi.restexample.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Director;
import es.udc.lbd.asi.restexample.model.repository.DirectorDAO;
import es.udc.lbd.asi.restexample.model.service.dto.DirectorDTO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class DirectorService implements DirectorServiceInterface {
	@Autowired
	private DirectorDAO directorDAO;
	
	@Transactional(readOnly = false)
	public DirectorDTO save(DirectorDTO director) {
		Director bdDirector = new Director(director.getName(), director.getSurname1(), director.getSurname2(), director.getManagment());
		directorDAO.save(bdDirector);
		return new DirectorDTO(bdDirector);
	}
}
