package es.udc.lbd.asi.restexample.model.service;

import java.util.List;

import javax.management.InstanceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.User;
import es.udc.lbd.asi.restexample.model.repository.MovieDAO;
import es.udc.lbd.asi.restexample.model.repository.UserDAO;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserService implements UserServiceInterface{

  @Autowired
    private UserDAO userDAO;

    public User findByLogin(String login) throws InstanceNotFoundException {
        return userDAO.findByLogin(login);
    }
    
    public User save(User user) {
        return userDAO.save(user);
    }
   

}
