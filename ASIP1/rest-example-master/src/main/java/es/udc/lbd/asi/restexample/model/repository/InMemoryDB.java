package es.udc.lbd.asi.restexample.model.repository;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import es.udc.lbd.asi.restexample.model.domain.Movie;


public class InMemoryDB {
    public static Map<Long, Movie> movie = new HashMap<Long, Movie>();
    

    public static AtomicLong idGenerator = new AtomicLong(0);

    static {
        movie.put(idGenerator.addAndGet(1), new Movie(idGenerator.get(), "Elite", "hsdaiuwgfdekuhdu",160,new Date(2015-11-04)));
        movie.put(idGenerator.addAndGet(1), new Movie(idGenerator.get(), "The rain","hsdaiuwgfdekuhdu",160,new Date(2015-11-04)));
        movie.put(idGenerator.addAndGet(1), new Movie(idGenerator.get(), "Las chicas del cable","hsdaiuwgfdekuhdu",160,new Date(2015-11-04)));
        movie.put(idGenerator.addAndGet(1), new Movie(idGenerator.get(), "Los 100", "hsdaiuwgfdekuhdu", 160,new Date(2015-11-04)));
        movie.put(idGenerator.addAndGet(1), new Movie(idGenerator.get(), "Vivir sin permiso", "hsdaiuwgfdekuhdu", 160,new Date(2015-11-04)));
        
    }
}
