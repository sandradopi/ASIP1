package es.udc.lbd.asi.restexample.model.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.access.prepost.PreAuthorize;

import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.NormalUser;
import es.udc.lbd.asi.restexample.model.domain.Status;
import es.udc.lbd.asi.restexample.model.domain.TipoStatus;
import es.udc.lbd.asi.restexample.model.domain.UserAuthority;
import es.udc.lbd.asi.restexample.model.domain.User_;
import es.udc.lbd.asi.restexample.model.domain.Actor;
import es.udc.lbd.asi.restexample.model.domain.Director;
import es.udc.lbd.asi.restexample.model.repository.ActorDAO;
import es.udc.lbd.asi.restexample.model.repository.DirectorDAO;
import es.udc.lbd.asi.restexample.model.repository.GenreDAO;
import es.udc.lbd.asi.restexample.model.repository.MovieDAO;
import es.udc.lbd.asi.restexample.model.repository.StatusDAO;
import es.udc.lbd.asi.restexample.model.repository.UserDAO;
import es.udc.lbd.asi.restexample.model.service.dto.ActorDTO;
import es.udc.lbd.asi.restexample.model.service.dto.DirectorDTO;
import es.udc.lbd.asi.restexample.model.service.dto.MovieDTO;
import es.udc.lbd.asi.restexample.model.service.dto.MovieListDTO;
import es.udc.lbd.asi.restexample.model.service.dto.NormalUserDTO;
import es.udc.lbd.asi.restexample.model.service.dto.NormalUserListUserDTO;
import es.udc.lbd.asi.restexample.model.service.dto.UserDTO;



@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MovieService implements MovieServiceInterface{

	
  @Autowired
  private MovieDAO movieDAO;
  
  @Autowired
  private StatusDAO statusDAO;
  
  @Autowired
  private GenreDAO genreDAO;
  
  @Autowired
  private ActorDAO actorDAO;
  
  @Autowired
  private DirectorDAO directorDAO;
  
  @Autowired
  private UserDAO userDAO;
  
  @Autowired
  private UserService userService;
  
  @Override
  public List<MovieDTO> findAll() {
	   try{
		   UserDTO user= userService.getCurrentUserWithAuthorityUser();
		   User_ usuario= userDAO.findById(user.getIdUser());
		   if(usuario.getAuthority()==UserAuthority.ADMIN ){
				return movieDAO.findAll().stream().map(movie -> new MovieDTO(movie)).collect(Collectors.toList());
	    	} else{
	    		return movieDAO.findAllUser().stream().map(movie -> new MovieDTO(movie)).collect(Collectors.toList());
	    	}
	   }catch(Exception e){
		   return movieDAO.findAllUser().stream().map(movie -> new MovieDTO(movie)).collect(Collectors.toList());
	   }
		
		
		
    }
  
  @Override
	public List<MovieListDTO> findAllMedia() {
	  Long media=new Long(0);
	  Long mediaFinal=new Long(0);
	  Long valorationTotal= new Long(0);
	  List <MovieListDTO> movies;
	  
	  
	 movies= movieDAO.findAllUser().stream().map(movie -> new MovieListDTO(movie)).collect(Collectors.toList());
	   
	  
	  for(MovieListDTO m:movies){
		  media=new Long(0);
		  valorationTotal= new Long(0);
		  
		  Movie bdMovie = movieDAO.findById(m.getIdMovie());
		  List<Status> status= statusDAO.findByMovies(bdMovie);
		  if(status.size()!=0){
			  for(Status s:status){
				  	if(s.getValoration()!=null){
					 media=media+ s.getValoration();
					 valorationTotal=valorationTotal+1;
					 }
				  
				  }
			  if(media==0 && valorationTotal==0){
				  mediaFinal=new Long(0);
			  }else{
			  mediaFinal= media/valorationTotal;}
			  }
		  else{
			  mediaFinal=new Long(0);
		  }
		 
		 m.setMedia(mediaFinal);
		  
	  }
	  return movies;
	}
  	
   @Override	
    public MovieDTO findById(Long idMovie)  {
    	 return new MovieDTO(movieDAO.findById(idMovie));
    }
   
   @Override
    public Long findAverage(Long idMovie)  {
	  Long media=new Long(0);
	  Long mediaFinal=new Long(0);
	  Long valorationTotal= new Long(0);
	  
	  Movie bdMovie = movieDAO.findById(idMovie);
	  List<Status> status= statusDAO.findByMovies(bdMovie);
	  if(status.size()!=0){
		  for(Status s:status){
			  	if(s.getValoration()!=null){
				 media=media+ s.getValoration();
				 valorationTotal=valorationTotal+1;
				 }
			  
			  }
		  if(media==0 && valorationTotal==0){
			  return mediaFinal=new Long(0);
		  }else{
		  return mediaFinal= media/valorationTotal;
		  }
		  }else{
	  return mediaFinal=new Long(0);}
   	 
   }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional(readOnly = false)
    @Override
    public MovieDTO save(MovieDTO movie) {	
    	Movie bdMovie = new Movie(movie.getHidden(), movie.getName(), movie.getSummary(), movie.getDuration(), movie.getData());
    	bdMovie.setGenre(genreDAO.findById(movie.getGenre().getIdGenre()));
    	bdMovie.setHidden(false);
    	Set<Actor> auxiliarA = new HashSet<Actor>();
     	Set<Director> auxiliarD = new HashSet<Director>();
     	
     	for(ActorDTO a: movie.getParticipantes()){
     		auxiliarA.add(actorDAO.findById(a.getIdActor()));
         }
     	
     	for(DirectorDTO d: movie.getDirigentes()){
     		auxiliarD.add(directorDAO.findById(d.getIdDirector()));
         }
     	
     	bdMovie.setParticipantes(auxiliarA);
     	bdMovie.setDirigentes(auxiliarD);
    	
    	
    	movieDAO.save(bdMovie);
        return new MovieDTO(bdMovie);
    }
    
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional(readOnly = false)
    @Override
    public MovieDTO update(MovieDTO movie){
        Movie bdMovie = movieDAO.findById(movie.getIdMovie());
        bdMovie.setName(movie.getName());
        bdMovie.setSummary(movie.getSummary());
        bdMovie.setDuration(movie.getDuration());
        bdMovie.setData(movie.getData());
        bdMovie.setHidden(movie.getHidden());
        bdMovie.setGenre(genreDAO.findById(movie.getGenre().getIdGenre()));
        bdMovie.getParticipantes().clear();
        movie.getParticipantes().forEach(actor -> {
            bdMovie.getParticipantes().add(actorDAO.findById(actor.getIdActor()));
        });
        bdMovie.getDirigentes().clear();
        movie.getDirigentes().forEach(director -> {
            bdMovie.getDirigentes().add(directorDAO.findById(director.getIdDirector()));
        });
        movieDAO.save(bdMovie);
        return new MovieDTO(bdMovie);
        }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional(readOnly = false)
    @Override
    public void deleteById(Long idMovie) {
		Movie bdMovie = movieDAO.findById(idMovie);
		try{
			List<Status> status= statusDAO.findByMovies(bdMovie);
			for(Status state: status){
			statusDAO.delete(state);
			}
    		movieDAO.deleteById(idMovie);
		}catch(Exception e){
			movieDAO.deleteById(idMovie);
		}
    }
    
    @PreAuthorize("hasAuthority('USER')")
	@Override
	public List<MovieListDTO> findAllMoviesType(String tipo) {
    	TipoStatus estado = null;
    	 Long media=new Long(0);
    	 Long mediaFinal=new Long(0);
    	 Long valorationTotal= new Long(0);
   	  
    	NormalUserDTO usuario= userService.getCurrentUserWithoutAuthority();
		NormalUser usuarioNormal= userDAO.findByIdNormal(usuario.getIdUser());
		if (tipo.equals("VISTA")){
			estado= TipoStatus.VISTA;
		}else if(tipo.equals("PENDIENTE")){
			estado= TipoStatus.PENDIENTE;
		}
		
    	List<MovieListDTO> movies= movieDAO.findAllMoviesType(usuarioNormal,estado).stream().map(movie -> new MovieListDTO(movie)).collect(Collectors.toList());
    	
    	for(MovieListDTO m:movies){
  		  media=new Long(0);
  		  valorationTotal= new Long(0);
  		  
  		  Movie bdMovie = movieDAO.findById(m.getIdMovie());
  		  List<Status> status= statusDAO.findByMovies(bdMovie);
  		  if(status.size()!=0){
  			  for(Status s:status){
  				  	if(s.getValoration()!=null){
  					 media=media+ s.getValoration();
  					 valorationTotal=valorationTotal+1;
  					 }
  				  
  				  }
  			  if(media==0 && valorationTotal==0){
  				  mediaFinal=new Long(0);
  			  }else{
  			  mediaFinal= media/valorationTotal;}
  			  }
  		  else{
  			  mediaFinal=new Long(0);
  		  }
  		 
  		 m.setMedia(mediaFinal);
  		  
  	  }
		return movies; 
	}

	
	@PreAuthorize("hasAuthority('USER')")
	@Override
	public List<MovieListDTO> findAllVistasVote() {
		Long media=new Long(0);
		Long mediaFinal=new Long(0);
		Long valorationTotal= new Long(0);
		NormalUserDTO usuario= userService.getCurrentUserWithoutAuthority();
		NormalUser usuarioNormal= userDAO.findByIdNormal(usuario.getIdUser());
		List<MovieListDTO> movies= movieDAO.findAllVistasVote(usuarioNormal).stream().map(movie -> new MovieListDTO(movie)).collect(Collectors.toList());
		for(MovieListDTO m:movies){
	  		  media=new Long(0);
	  		  valorationTotal= new Long(0);
	  		  
	  		  Movie bdMovie = movieDAO.findById(m.getIdMovie());
	  		  List<Status> status= statusDAO.findByMovies(bdMovie);
	  		  if(status.size()!=0){
	  			  for(Status s:status){
	  				  	if(s.getValoration()!=null){
	  					 media=media+ s.getValoration();
	  					 valorationTotal=valorationTotal+1;
	  					 }
	  				  
	  				  }
	  			  if(media==0 && valorationTotal==0){
	  				  mediaFinal=new Long(0);
	  			  }else{
	  			  mediaFinal= media/valorationTotal;}
	  			  }
	  		  else{
	  			  mediaFinal=new Long(0);
	  		  }
	  		 
	  		 m.setMedia(mediaFinal);
	  		  
	  	  }
		return movies; 
	}
	
	

	

}
