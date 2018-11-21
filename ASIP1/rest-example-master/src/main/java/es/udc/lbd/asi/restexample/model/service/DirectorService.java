package es.udc.lbd.asi.restexample.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Director;
import es.udc.lbd.asi.restexample.model.repository.DirectorDAO;
import es.udc.lbd.asi.restexample.model.service.dto.ActorDTO;
import es.udc.lbd.asi.restexample.model.service.dto.DirectorDTO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class DirectorService implements DirectorServiceInterface {
	@Autowired
	private DirectorDAO directorDAO;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@Transactional(readOnly = false)
	public DirectorDTO save(DirectorDTO director) {
		Director bdDirector = new Director(director.getName(), director.getSurname1(), director.getSurname2());
		
		directorDAO.save(bdDirector);
		return new DirectorDTO(bdDirector);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<DirectorDTO> findAll() {
    	return directorDAO.findAll().stream().map(director-> new DirectorDTO(director)).collect(Collectors.toList());
    }
	
	@PreAuthorize("hasAuthority('ADMIN')")
	public DirectorDTO findById(Long idDirector)  {
   	 return new DirectorDTO(directorDAO.findById(idDirector));
   }
	
	 @PreAuthorize("hasAuthority('ADMIN')")
	    @Transactional(readOnly = false)
	    @Override
	    public DirectorDTO update(DirectorDTO director){
	        Director bdDirector = directorDAO.findById(director.getIdDirector());
	        bdDirector.setName(director.getName());
	        bdDirector.setSurname1(director.getSurname1());
	        bdDirector.setSurname2(director.getSurname2());
	       
	        directorDAO.save(bdDirector);
	        return new DirectorDTO(bdDirector);
	        }
	
	 @PreAuthorize("hasAuthority('ADMIN')")
	    @Transactional(readOnly = false)
	    @Override
	    public String deleteById(Long idDirector) {
		 	String resultado;
			Director bdDirector = directorDAO.findById(idDirector);
			if (bdDirector.getManagment().size()==0){
				directorDAO.deleteById(idDirector);
				resultado="exito";
				return resultado;
			}
			else {
				resultado="fracaso";
				return resultado;
			}
	    }
	    
}
