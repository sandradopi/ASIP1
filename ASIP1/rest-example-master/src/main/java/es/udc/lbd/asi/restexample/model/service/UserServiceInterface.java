package es.udc.lbd.asi.restexample.model.service;

import java.util.List;

import javax.management.InstanceNotFoundException;
import es.udc.lbd.asi.restexample.model.domain.Movie;
import es.udc.lbd.asi.restexample.model.domain.User;

public interface UserServiceInterface {
    public User findByLogin(String login) throws InstanceNotFoundException;;
    public User save(User user);

}
