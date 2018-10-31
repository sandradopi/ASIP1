package es.udc.lbd.asi.restexample.model.repository;

import java.util.List;

import es.udc.lbd.asi.restexample.model.domain.NormalUser;
import es.udc.lbd.asi.restexample.model.domain.User_;


public interface UserDAO {
    public User_ findByLogin(String Login) ;
    public List <User_> findAll();
    public void save(User_ user);
    public User_ findById(Long idUser) ;
    public NormalUser findByIdNormal(Long idUser) ;
   
}
