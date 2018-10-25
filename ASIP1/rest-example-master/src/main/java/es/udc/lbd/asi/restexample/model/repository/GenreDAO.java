package es.udc.lbd.asi.restexample.model.repository;
import es.udc.lbd.asi.restexample.model.domain.Genre;

public interface GenreDAO {
	public void save(Genre genre);
	public Genre findById(Long idGenre);
}
