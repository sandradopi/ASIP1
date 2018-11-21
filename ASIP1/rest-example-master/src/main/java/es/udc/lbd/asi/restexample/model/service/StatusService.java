package es.udc.lbd.asi.restexample.model.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.NormalUser;
import es.udc.lbd.asi.restexample.model.domain.Status;
import es.udc.lbd.asi.restexample.model.domain.TipoStatus;
import es.udc.lbd.asi.restexample.model.repository.MovieDAO;
import es.udc.lbd.asi.restexample.model.repository.StatusDAO;
import es.udc.lbd.asi.restexample.model.repository.UserDAO;
import es.udc.lbd.asi.restexample.model.service.dto.NormalUserDTO;
import es.udc.lbd.asi.restexample.model.service.dto.StatusDTO;

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
	public void save(Long MovieId, String statu) {
		Movie bdMovie = movieDAO.findById(MovieId);
		NormalUserDTO usuario= userService.getCurrentUserWithoutAuthority();
		NormalUser usuarioNormal= userDAO.findByIdNormal(usuario.getIdUser());
		Status bdStatus = null;
		
		if(statusDAO.findByMovieUser(bdMovie,usuarioNormal)==null){
			if(statu.equals("novista-vista")){
				bdStatus = new Status(null,TipoStatus.VISTA);
			} else if(statu.equals("nopendiente-pendiente")){
				bdStatus = new Status(null,TipoStatus.PENDIENTE);	
			}
				bdStatus.setMovie(bdMovie);
				bdStatus.setNormalUser(usuarioNormal);
				statusDAO.save(bdStatus);
		}
				
				
	}
	
	@Override
	@Transactional(readOnly = false)
	public void deleteByIdMovieUser(Long idMovie) {
		Movie bdMovie = movieDAO.findById(idMovie);
		NormalUserDTO usuario= userService.getCurrentUserWithoutAuthority();
		NormalUser usuarioNormal= userDAO.findByIdNormal(usuario.getIdUser());
		 if((statusDAO.findByMovieUser(bdMovie,usuarioNormal).getValoration()==null)){
			Status status= statusDAO.findByMovieUser(bdMovie, usuarioNormal);
	    	statusDAO.delete(status);}
	    }

	@PreAuthorize("hasAuthority('USER')")
	@Transactional(readOnly = false)
	@Override
	public StatusDTO update(Long idMovie, Integer valoracion) {
		Movie bdMovie = movieDAO.findById(idMovie);
		NormalUserDTO usuario= userService.getCurrentUserWithoutAuthority();
		NormalUser usuarioNormal= userDAO.findByIdNormal(usuario.getIdUser());
		Status bdStatus= statusDAO.findByMovieUser(bdMovie, usuarioNormal);
		bdStatus.setValoration(valoracion);
		statusDAO.save(bdStatus);
		StatusDTO status = new StatusDTO(statusDAO.findByMovieUser(bdMovie, usuarioNormal));
		return status;
		
		
	}
	
	
	@Override
	public StatusDTO findByMovieUser(Long idMovie) {
		Movie bdMovie = movieDAO.findById(idMovie);
		NormalUserDTO usuario= userService.getCurrentUserWithoutAuthority();
		NormalUser usuarioNormal= userDAO.findByIdNormal(usuario.getIdUser());
		try{
			return new StatusDTO(statusDAO.findByMovieUser(bdMovie, usuarioNormal));
		}catch(Exception e){
			return null;
		}
		
	
		
	}

	
	@Override
	public Long countVistas(String login) {
		return statusDAO.findByMovieUserVista(login);
		
	}
	
	@Override
	public Long countVistasValoration(String login) {
		return statusDAO.findByMovieUserVistaValoration(login);
		
	}
	
	@Override
	public Long countPendientes(String login) {
		return statusDAO.findByMovieUserPendiente(login);
		
	}
	

		
	}
	
	
	

