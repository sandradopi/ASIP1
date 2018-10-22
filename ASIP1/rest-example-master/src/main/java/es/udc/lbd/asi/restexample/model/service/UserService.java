package es.udc.lbd.asi.restexample.model.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.management.InstanceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.User;
import es.udc.lbd.asi.restexample.model.repository.MovieDAO;
import es.udc.lbd.asi.restexample.model.repository.UserDAO;
import es.udc.lbd.asi.restexample.model.service.dto.UserDTO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserService implements UserServiceInterface{

  @Autowired
    private UserDAO userDAO;

    public User findByLogin(String login) throws InstanceNotFoundException {
        return new UserDTO(userDAO.findByLogin(login));
    }
    
    public UserDTO save(UserDTO user) {
    	User bdUser = new User(user.getLogin(), user.getPassword(), user.getName(), user.getSurname1(), user.getSurname2(), user.getPhone());
        userDAO.save(bdUser);
        return new UserDTO(bdUser);
    }

	@Override
	public List<UserDTO> findAll() {
		return userDAO.findAll().stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return null;
	}
   

}
