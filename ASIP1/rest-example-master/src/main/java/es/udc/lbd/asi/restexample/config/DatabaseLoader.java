package es.udc.lbd.asi.restexample.config;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
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
     * transaciones.
     */
    @PostConstruct
    public void init() {
        databaseLoader.loadData();
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void loadData() {
//crear objetos y añadirlos
    
        
    }
}
