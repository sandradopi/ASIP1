package es.udc.lbd.asi.restexample.model.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Director;
import es.udc.lbd.asi.restexample.model.domain.Genre;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.Status;
import es.udc.lbd.asi.restexample.model.repository.ActorDAO;
import es.udc.lbd.asi.restexample.model.repository.MovieDAO;
import es.udc.lbd.asi.restexample.model.repository.StatusDAO;
import es.udc.lbd.asi.restexample.model.service.dto.ActorDTO;
import es.udc.lbd.asi.restexample.model.service.dto.GenreDTO;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;
import es.udc.lbd.asi.restexample.model.service.dto.StatusDTO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class StatusService implements StatusServiceInterface {
	
	@Autowired
	private StatusDAO statusDAO;
	@Autowired
	private MovieDAO movieDAO;

	 @PreAuthorize("hasAuthority('USER')")
	@Transactional(readOnly = false)
	public StatusDTO save(StatusDTO status) {
		Status bdStatus = new Status(status.getValoration(),status.getType());
		bdStatus.setMovie(movieDAO.findById(status.getMovie().getIdMovie()));
		bdStatus.getNormalUser();
		statusDAO.save(bdStatus);
		return new StatusDTO(bdStatus);
	}
	
}
