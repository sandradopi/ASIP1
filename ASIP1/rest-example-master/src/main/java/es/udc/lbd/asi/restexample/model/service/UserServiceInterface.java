package es.udc.lbd.asi.restexample.model.service;

import java.util.List;

import es.udc.lbd.asi.restexample.model.exception.UserLoginExistsException;
import es.udc.lbd.asi.restexample.model.service.dto.NormalUserDTO;


public interface UserServiceInterface {
    public List<NormalUserDTO> findAll();
    public void registerUser(String login,String email, String password, boolean isAdmin) throws UserLoginExistsException;
    public void registerUser(String login,String email, String password) throws UserLoginExistsException;
  


}
