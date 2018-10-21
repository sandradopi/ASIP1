package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.management.InstanceNotFoundException;
import org.springframework.stereotype.Repository;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.User;

@Repository
public class UserDAOHibernate implements UserDAO {


	@Override
	public User findByLogin(String login) throws InstanceNotFoundException{
		return null;
		 
	}
	 @Override
	    public User save(User user) {
		 return null;}
	@Override
	public List<User> findAll() {
		return null;
	}

	   


   
}
