package es.udc.lbd.asi.restexample.model.service;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Transactional(readOnly = false)
	public GenreDTO save(GenreDTO genre) {
		Genre bdGenre = new Genre(genre.getType());
		genreDAO.save(bdGenre);
		return new GenreDTO(bdGenre);
	}
}
