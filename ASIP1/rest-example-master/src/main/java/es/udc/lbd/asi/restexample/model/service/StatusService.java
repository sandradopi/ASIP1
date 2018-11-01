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
import es.udc.lbd.asi.restexample.model.domain.NormalUser;
import es.udc.lbd.asi.restexample.model.domain.Status;
import es.udc.lbd.asi.restexample.model.domain.User_;
import es.udc.lbd.asi.restexample.model.domain.TipoStatus;
import es.udc.lbd.asi.restexample.model.repository.ActorDAO;
import es.udc.lbd.asi.restexample.model.repository.MovieDAO;
import es.udc.lbd.asi.restexample.model.repository.StatusDAO;
import es.udc.lbd.asi.restexample.model.repository.UserDAO;
import es.udc.lbd.asi.restexample.model.service.dto.ActorDTO;
import es.udc.lbd.asi.restexample.model.service.dto.GenreDTO;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;
import es.udc.lbd.asi.restexample.model.service.dto.NormalUserDTO;
import es.udc.lbd.asi.restexample.model.service.dto.StatusDTO;
import es.udc.lbd.asi.restexample.model.service.dto.UserDTO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class StatusService implements StatusServiceInterface {
	
	@Autowired
	private StatusDAO statusDAO;
	@Autowired
	private MovieDAO movieDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserService userService;

	@PreAuthorize("hasAuthority('USER')")
	@Transactional(readOnly = false)
	public void save(Long MovieId, TipoStatus STATE) {
		
		Status bdStatus = new Status(null,STATE);
		bdStatus.setMovie(movieDAO.findById(MovieId));
		NormalUserDTO usuario= userService.getCurrentUserWithoutAuthority();
		NormalUser usuarioNormal= userDAO.findByIdNormal(usuario.getIdUser());
		bdStatus.setNormalUser(usuarioNormal);
		statusDAO.save(bdStatus);

	}

	@Override
	@Transactional(readOnly = false)
	public void deleteByIdMovieUser(Long movieId) {
			NormalUserDTO usuario= userService.getCurrentUserWithoutAuthority();
			NormalUser usuarioNormal= userDAO.findByIdNormal(usuario.getIdUser());
			Status status= statusDAO.findByMovieUser(movieId, usuarioNormal);
	    	statusDAO.delete(status);
	    }
		
	}
	
	
	

