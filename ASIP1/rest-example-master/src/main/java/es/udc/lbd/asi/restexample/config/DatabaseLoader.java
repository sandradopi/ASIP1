package es.udc.lbd.asi.restexample.config;

import java.sql.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.User;
import es.udc.lbd.asi.restexample.model.repository.MovieDAO;
import es.udc.lbd.asi.restexample.model.repository.UserDAO;

@Configuration
public class DatabaseLoader {
    @Autowired
    private UserDAO userService;

    @Autowired
    private MovieDAO movieService;

    @Autowired
    private DatabaseLoader databaseLoader;

    /*
     * Para hacer que la carga de datos sea transacional, hay que cargar el propio
     * objeto como un bean y lanzar el método una vez cargado, ya que en el
     * PostConstruct (ni similares) se tienen en cuenta las anotaciones de
     * transaciones. idGenerator.get(),"sandradopi", "asi", "Sandra","Dopico", "Cantarero",698787878)
     */
    @PostConstruct
    public void init() {
        databaseLoader.loadData();
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void loadData() {
    	//userService.save(new User("sandradopi", "asi", "Sandra","Dopico", "Cantarero",698787878));
    	//userService.save(new User("sarayramilo", "asi", "Saray","Ramilo", "Loureiro",698121212));
       

        movieService.save(new Movie("Harry Potter", "La pelicula trata sobre...",160,new Date(2015-11-04)));
        movieService.save(new Movie("Infiltados en Miami", "La pelicula trata sobre...",250,new Date(2016-02-03)));
        
    }
}
