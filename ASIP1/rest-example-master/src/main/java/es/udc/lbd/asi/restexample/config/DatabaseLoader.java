package es.udc.lbd.asi.restexample.config;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import es.udc.lbd.asi.restexample.model.service.UserService;


import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Director;
import es.udc.lbd.asi.restexample.model.domain.Genre;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.UserNoti;
import es.udc.lbd.asi.restexample.model.exception.PasswordTooShort;
import es.udc.lbd.asi.restexample.model.exception.RequiredFieldsException;
import es.udc.lbd.asi.restexample.model.exception.UserLoginEmailExistsException;
import es.udc.lbd.asi.restexample.model.repository.DirectorDAO;
import es.udc.lbd.asi.restexample.model.repository.GenreDAO;
import es.udc.lbd.asi.restexample.model.repository.MovieDAO;
import es.udc.lbd.asi.restexample.model.repository.ActorDAO;

@Configuration
public class DatabaseLoader {
	private final Logger logger = LoggerFactory.getLogger(DatabaseLoader.class);

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
    
    @Autowired
    private UserService userService;

    /*
     * Para hacer que la carga de datos sea transacional, hay que cargar el propio
     * objeto como un bean y lanzar el método una vez cargado, ya que en el
     * PostConstruct (ni similares) se tienen en cuenta las anotaciones de
     * transaciones.
     */
    @PostConstruct
    public void init() throws ParseException, RequiredFieldsException, PasswordTooShort {
    	 try {
             databaseLoader.loadData();
         } catch (UserLoginEmailExistsException e) {
             logger.error(e.getMessage(), e);
         }
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void loadData() throws UserLoginEmailExistsException, ParseException, RequiredFieldsException, PasswordTooShort{
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
    	
    userService.registerUser("pepe","pepe@pepe.com", "pepe", true, sdf.parse("2018-11-28"), null);
    userService.registerUser("maria","maria@maria.com","maria",true, sdf.parse("2018-11-15"), null);
    userService.registerUser("laura", "guajndos@gmail.com","laura", false, sdf.parse("2018-11-18"), UserNoti.EMAIL);
    userService.registerUser("sandra","vicfic23@gmail.com","sandra", false, sdf.parse("2018-11-18"), UserNoti.SMS);
    userService.registerUser("lucas", "sandra111@outlook.com","lucas", false, sdf.parse("2018-11-18"),null);
 
    Genre genre1= new Genre("Drama");
    genreService.save(genre1);	
    Genre genre2= new Genre("Acción");
    genreService.save(genre2);	
    Actor actor1= new Actor("Angelina","Jolie");
    actorService.save(actor1);
    Actor actor2= new Actor("Brad","Pitt");
    actorService.save(actor2);
    Director director1= new Director("Alex","De la Iglesia");
    directorService.save(director1);
    
    Set <Actor>participantes = new <Actor> HashSet();
    participantes.add(actor1);
    participantes.add(actor2);
    Set <Director>dirigentes = new <Director> HashSet();
    dirigentes.add(director1);
    
    movieService.save
    (new Movie(false,"Spiderman","La pelicula va sobre...",160, sdf.parse("2018-11-29"),
    		genre1, participantes,dirigentes,"/rest-example-master/src/main/java/es/udc/lbd/asi/restexample/repository/util/images/movie1.jpg"));
    
    movieService.save
    (new Movie(false,"Wonder Woman","La pelicula va sobre...",250, sdf.parse("2018-11-29"),
    		genre2, participantes,dirigentes,"/rest-example-master/src/main/java/es/udc/lbd/asi/restexample/repository/util/images/movie2.jpg"));
    
    movieService.save
    (new Movie(false,"Harry Potter","La pelicula va sobre...",250,  sdf.parse("2018-11-02"),
    		genre2, participantes,dirigentes,"/rest-example-master/src/main/java/es/udc/lbd/asi/restexample/repository/util/images/movie3.jpg"));
   
    
    movieService.save
    (new Movie(false,"Infiltrados en miami","La pelicula va sobre...",250,  sdf.parse("2018-11-02"),
    		genre2, participantes,dirigentes,"/rest-example-master/src/main/java/es/udc/lbd/asi/restexample/repository/util/images/movie4.jpg"));
    
    
    movieService.save
    (new Movie(false,"Lara Croft","La pelicula va sobre...",250,  sdf.parse("2018-11-02"),
    		genre2, participantes,dirigentes,"/rest-example-master/src/main/java/es/udc/lbd/asi/restexample/repository/util/images/movie5.jpg"));
    
    movieService.save
    (new Movie(false,"Fast and Furious","La pelicula va sobre...",250,  sdf.parse("2018-11-02"),
    		genre2, participantes,dirigentes,"/rest-example-master/src/main/java/es/udc/lbd/asi/restexample/repository/util/images/movie6.jpg"));
    
   
    }


    
    
}
