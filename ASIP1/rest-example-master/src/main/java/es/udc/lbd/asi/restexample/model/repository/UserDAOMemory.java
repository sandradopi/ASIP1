package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.management.InstanceNotFoundException;
import org.springframework.stereotype.Repository;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.User;

@Repository
public class UserDAOMemory implements UserDAO {


	@Override
	public User findByLogin(String login) throws InstanceNotFoundException{
		 return InMemoryDB.user.get(login);
		 
	}
	 @Override
	    public User save(User user) {
	  
		 if (user.getId() == null) {
	            user.setId(InMemoryDB.idGenerator.addAndGet(1));
	        }
	        InMemoryDB.user.put(user.getId(), user);
	        return user;
	    }

	   


   
}
