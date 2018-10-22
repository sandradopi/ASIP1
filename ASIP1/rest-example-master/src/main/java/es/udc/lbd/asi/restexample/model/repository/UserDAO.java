package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import javax.management.InstanceNotFoundException;

import es.udc.lbd.asi.restexample.model.domain.User;


public interface UserDAO {
    public User findByLogin(String Login) throws InstanceNotFoundException;
    public List <User> findAll();
    public void save(User user);
    public User findById(Long idUser);
    public void deleteById(Long idUser);
}
