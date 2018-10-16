package es.udc.lbd.asi.restexample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.service.MovieService;

@Component
public class MovieServiceClient {
	@Autowired
	  private MovieService movieService;

	  /** Prints the films */
	  public void showMovies() {

	    //List<Movie> latestMovies = movieService.findAll();

	   // for (Movie n : latestMovies)
	     // System.out.println(n);
	  }
}
