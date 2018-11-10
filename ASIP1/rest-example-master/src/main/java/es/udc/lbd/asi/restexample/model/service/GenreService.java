package es.udc.lbd.asi.restexample.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.udc.lbd.asi.restexample.model.domain.Genre;
import es.udc.lbd.asi.restexample.model.repository.GenreDAO;
import es.udc.lbd.asi.restexample.model.service.dto.GenreDTO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class GenreService implements GenreServiceInterface{
	@Autowired
	private GenreDAO genreDAO;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@Transactional(readOnly = false)
	public GenreDTO save(GenreDTO genre) {
		Genre bdGenre = new Genre(genre.getType());
		genreDAO.save(bdGenre);
		return new GenreDTO(bdGenre);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<GenreDTO> findAll() {
    	return genreDAO.findAll().stream().map(genre -> new GenreDTO(genre)).collect(Collectors.toList());
    }
}
