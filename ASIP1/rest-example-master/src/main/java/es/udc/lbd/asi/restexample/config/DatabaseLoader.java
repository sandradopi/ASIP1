package es.udc.lbd.asi.restexample.config;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Director;
import es.udc.lbd.asi.restexample.model.domain.Genre;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.repository.DirectorDAO;
import es.udc.lbd.asi.restexample.model.repository.GenreDAO;
import es.udc.lbd.asi.restexample.model.repository.MovieDAO;
import es.udc.lbd.asi.restexample.model.repository.UserDAO;
import es.udc.lbd.asi.restexample.model.repository.ActorDAO;

@Configuration
public class DatabaseLoader {
    @Autowired
    private UserDAO userService;

    @Autowired
    private MovieDAO movieService;
    
    @Autowired
    private GenreDAO genreService;
    
    @Autowired
    private DirectorDAO directorService;
    
    @Autowired
    private ActorDAO actorService;

    @Autowired
    private DatabaseLoader databaseLoader;

    /*
     * Para hacer que la carga de datos sea transacional, hay que cargar el propio
     * objeto como un bean y lanzar el método una vez cargado, ya que en el
     * PostConstruct (ni similares) se tienen en cuenta las anotaciones de
     * transaciones.
     */
    @PostConstruct
    public void init() {
        databaseLoader.loadData();
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void loadData() {
 
    Genre genre1= new Genre("Drama");
    genreService.save(genre1);	
    Genre genre2= new Genre("Acción");
    genreService.save(genre2);	
    Actor actor1= new Actor("Angelina","Jolie");
    actorService.save(actor1);
    Actor actor2= new Actor("Brad","Pitt");
    actorService.save(actor2);
    Director director1= new Director("Alex","Iglesias");
    directorService.save(director1);
    
    Set <Actor>participantes = new <Actor> HashSet();
    participantes.add(actor1);
    participantes.add(actor2);
    Set <Director>dirigentes = new <Director> HashSet();
    dirigentes.add(director1);
    
    
    movieService.save
    (new Movie(false,"Spiderman","La pelicula va sobre...",160, new Date(2015-11-04),
    		genre1, participantes,dirigentes));
    
    movieService.save
    (new Movie(false,"WonderWoman","La pelicula va sobre...",250, new Date(2018-12-06),
    		genre2, participantes,dirigentes));
    
    movieService.save
    (new Movie(false,"Harry Potter","La pelicula va sobre...",250, new Date(2018-12-06),
    		genre2, participantes,dirigentes));
    }
    
    
}
