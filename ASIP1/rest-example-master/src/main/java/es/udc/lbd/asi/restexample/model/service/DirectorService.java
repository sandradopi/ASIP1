package es.udc.lbd.asi.restexample.model.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Director;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.repository.DirectorDAO;
import es.udc.lbd.asi.restexample.model.repository.MovieDAO;
import es.udc.lbd.asi.restexample.model.service.dto.DirectorDTO;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class DirectorService implements DirectorServiceInterface {
	@Autowired
	private DirectorDAO directorDAO;
	@Autowired
	private MovieDAO movieDAO;
	
	
	@Transactional(readOnly = false)
	public DirectorDTO save(DirectorDTO director) {
		Director bdDirector = new Director(director.getName(), director.getSurname1(), director.getSurname2());
		Set<Movie> auxiliarM = new HashSet<Movie>();
		for(MovieDTO m: director.getManagment()){
    		auxiliarM.add(movieDAO.findById(m.getIdMovie()));
        }
		bdDirector.setManagment(auxiliarM);
		directorDAO.save(bdDirector);
		return new DirectorDTO(bdDirector);
	}
}
